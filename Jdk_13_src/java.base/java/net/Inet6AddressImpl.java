/*
 * Copyright (c) 2002, 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package java.net;

import java.io.IOException;
import static java.net.InetAddress.PREFER_IPV6_VALUE;
import static java.net.InetAddress.PREFER_SYSTEM_VALUE;

/*
 * Package private implementation of InetAddressImpl for dual
 * IPv4/IPv6 stack.
 * <p>
 * If InetAddress.preferIPv6Address is true then anyLocalAddress()
 * and localHost() will return IPv6 addresses, otherwise IPv4 addresses.
 *
 * loopbackAddress() will return the first valid loopback address in
 * [IPv6 loopback, IPv4 loopback] if InetAddress.preferIPv6Address is true,
 * else [IPv4 loopback, IPv6 loopback].
 * If neither are valid it will fallback to the first address tried.
 *
 * @since 1.4
 */
class Inet6AddressImpl implements InetAddressImpl {

    public native String getLocalHostName() throws UnknownHostException;

    public native InetAddress[] lookupAllHostAddr(String hostname)
        throws UnknownHostException;

    public native String getHostByAddr(byte[] addr) throws UnknownHostException;

    private native boolean isReachable0(byte[] addr, int scope, int timeout,
                                        byte[] inf, int ttl, int if_scope)
        throws IOException;

    public boolean isReachable(InetAddress addr, int timeout,
                               NetworkInterface netif, int ttl)
        throws IOException
    {
        byte[] ifaddr = null;
        int scope = -1;
        int netif_scope = -1;
        if (netif != null) {
            /*
             * Let's make sure we bind to an address of the proper family.
             * Which means same family as addr because at this point it could
             * be either an IPv6 address or an IPv4 address (case of a dual
             * stack system).
             */
            java.util.Enumeration<InetAddress> it = netif.getInetAddresses();
            InetAddress inetaddr = null;
            while (it.hasMoreElements()) {
                inetaddr = it.nextElement();
                if (inetaddr.getClass().isInstance(addr)) {
                    ifaddr = inetaddr.getAddress();
                    if (inetaddr instanceof Inet6Address) {
                        netif_scope = ((Inet6Address) inetaddr).getScopeId();
                    }
                    break;
                }
            }
            if (ifaddr == null) {
                // Interface doesn't support the address family of
                // the destination
                return false;
            }
        }
        if (addr instanceof Inet6Address)
            scope = ((Inet6Address) addr).getScopeId();
        return isReachable0(addr.getAddress(), scope, timeout, ifaddr, ttl, netif_scope);
    }

    public synchronized InetAddress anyLocalAddress() {
        if (anyLocalAddress == null) {
            if (InetAddress.preferIPv6Address == PREFER_IPV6_VALUE ||
                InetAddress.preferIPv6Address == PREFER_SYSTEM_VALUE) {
                anyLocalAddress = new Inet6Address();
                anyLocalAddress.holder().hostName = "::";
            } else {
                anyLocalAddress = (new Inet4AddressImpl()).anyLocalAddress();
            }
        }
        return anyLocalAddress;
    }

    public synchronized InetAddress loopbackAddress() {
        if (loopbackAddress == null) {
            boolean preferIPv6Address =
                InetAddress.preferIPv6Address == PREFER_IPV6_VALUE ||
                InetAddress.preferIPv6Address == PREFER_SYSTEM_VALUE;
            InetAddress loopback4 = (new Inet4AddressImpl()).loopbackAddress();
            InetAddress loopback6 = new Inet6Address("localhost",
                new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01});
            // Order the candidate addresses by preference.
            InetAddress[] addresses = preferIPv6Address
                ? new InetAddress[] {loopback6, loopback4}
                : new InetAddress[] {loopback4, loopback6};
            // In case of failure, default to the preferred address.
            loopbackAddress = addresses[0];
            // Pick the first candidate address that actually exists.
            for (InetAddress address : addresses) {
                try {
                    if (NetworkInterface.getByInetAddress(address) == null) {
                        continue;
                    }
                } catch (SocketException e) {
                    continue;
                }
                loopbackAddress = address;
                break;
            }
        }
        return loopbackAddress;
    }

    private InetAddress anyLocalAddress;
    private InetAddress loopbackAddress;
}
