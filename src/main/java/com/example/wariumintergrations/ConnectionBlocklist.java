package com.example.wariumintergrations;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Set;

public final class ConnectionBlocklist {
    private static final Set<BlockedEndpoint> BLOCKED_ENDPOINTS = Set.of();

    private ConnectionBlocklist() { }

    public static boolean isBlockedServerEndpoint(SocketAddress address) {
        InetSocketAddress inet = asInet(address);
        if (inet == null) {
            return false;
        }
        String host = inet.getHostString();
        String ip = resolveIp(inet);
        for (BlockedEndpoint endpoint : BLOCKED_ENDPOINTS) {
            if (endpoint.matchesHost(host, ip)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlockedClient(SocketAddress address) {
        InetSocketAddress inet = asInet(address);
        if (inet == null) {
            return false;
        }
        String host = inet.getHostString();
        String ip = resolveIp(inet);
        for (BlockedEndpoint endpoint : BLOCKED_ENDPOINTS) {
            if (endpoint.matchesHost(host, ip)) {
                return true;
            }
        }
        return false;
    }

    private static InetSocketAddress asInet(SocketAddress address) {
        if (address instanceof InetSocketAddress inet) {
            return inet;
        }
        return null;
    }

    private static String resolveIp(InetSocketAddress address) {
        InetAddress inetAddress = address.getAddress();
        if (inetAddress != null) {
            return inetAddress.getHostAddress();
        }
        return null;
    }

    private record BlockedEndpoint(String host) {
        boolean matchesHost(String host, String ip) {
            if (host != null && host.equalsIgnoreCase(this.host)) {
                return true;
            }
            return ip != null && ip.equals(this.host);
        }
    }
}
