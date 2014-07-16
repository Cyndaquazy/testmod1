package com.cyndaquazy.testmod.proxy;

/**
 * An interface that defines the methods all proxies must implement.
 * 
 * Minecraft has two internal sub-systems: one for the server, which handles most of the
 * calculations regarding player interactions, and the client, which receives the server
 * data and is charged with rendering on-screen graphics.
 * 
 * Proxies are the way of differentiating the two systems -- one for the server,
 * {@link ServerProxy}, and one for the client, {@link ClientProxy}. In addition,
 * there is also a {@link CommonProxy}, which implements this interface and provides
 * methods that the client and server have in common.
 * 
 * @author Cyndaquazy
 *
 */
public interface IProxy
{

}
