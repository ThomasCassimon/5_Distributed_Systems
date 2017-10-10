package Network;

/**
 * @author Thomas
 * @date 9/10/2017
 * <p>
 * Project: 5_Distributed_Systems
 * Package: Network
 * This Class describes a header of the protocol used to manage our distributed system.
 *      2 Bytes         4 Bytes             8 Bytes
 *  |<--------->|<-------------->|<-------------------------------->|
 *  +-----------+----------------+----------------------------------+
 *  |   VERSION |   LENGTH       |      MESSAGE CODE                |
 *  +-----------+----------------+----------------------------------+
 *  |                       MESSAGE DATA                            |
 *  +---------------------------------------------------------------+
 *  |<------------------------------------------------------------->|
 *                          Length - 14 Bytes
 *  General
 *  All data is sent little-endian.
 *
 *  Fields
 *
 *  VERSION
 *  The version field is used to identify the version of our protocol that the message was sent with.
 *  The version field is 2 bytes long.
 *
 *  LENGTH
 *  The length field indicates the length of the message, in bytes.
 *
 *  MESSAGE CODE
 *  A code identifying this message.
 *  This also tells the program how to parse the message.
 */
public class ProtocolHeader
{
	private static final int CURRENT_VERSION = 1;
}
