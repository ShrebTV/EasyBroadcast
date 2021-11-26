# EasyBroadcast
A small plugin to broadcast messages to all players on a server

Commands:
 /eb <send/fadein/stay/fadeout/subtitle/switchMode/cancel>
 /eb send <message> //sends the message to every player on the server via title.(requires permission "EB.sendBC")
 /eb <fadein/stay/fadeout> <?time> // time optional. if time is added, this command will set the timing of titles sent via the send command to that time (in ticks, 20 ticks = 1 second)
                                   // if time is not added you will be sent a chatmessage informing you of the currently set time. Using this command requires the permission "EB.editConfig"
/eb subtitle <?message> //message is optional. If message is added the subtitle for each broadcast sent via /eb send will be set to that message.
                        // if message is not added, you will be sent a chatmessage containing the current subtitle.
/eb switchMode //this will enable/disable titles for the player who uses the command. If Titles are enabled (default) the player will receive all broadcasts sent, if disabled the player will not be sent any broadcasts.
/eb cancel // clears the current title of the sender. requires the permission "EB.cancelTitleSelf", this is enabled by default tho.
/eb cancel all //clears the current title of ALL Players on a server. requires the permission "EB.cancelTitleAll"
