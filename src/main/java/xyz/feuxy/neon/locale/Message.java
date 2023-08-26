package xyz.feuxy.neon.locale;

public interface Message {

    SimpleMessage PLAYERS_ONLY = () -> "&cOnly players can use this command!";
    SimpleMessage INVALID_ARGUMENTS = () -> "&cInvalid arguments! Please try again.";
    SimpleMessage CMD_GAMEMODE_ALREADY = () -> "&cYou are already in &e%s &cmode!";
    SimpleMessage CMD_GAMEMODE_CHANGED = () -> "&aYour gamemode has been changed to &e%s&a!";
    SimpleMessage CMD_TIME_CHANGED = () -> "&aThe time has been changed to &e%s &ain &e%s&a!";
    SimpleMessage CMD_WEATHER_CHANGED = () -> "&aThe weather has been changed to &e%s &ain &e%s&a!";
    SimpleMessage CMD_FLY_SELF_ENABLED = () -> "&aYou have enabled fly mode!";
    SimpleMessage CMD_FLY_SELF_DISABLED = () -> "&cYou have disabled fly mode!";
    SimpleMessage CMD_BUTCHER = () -> "&aRemoved &e%s &aentities from &e%s &ain &e%s &ams.";
    SimpleMessage CMD_BUTCHER_LOG = () -> "&cRemoved &e%s &cat &e%s&c, &e%s&c, &e%s &cfrom &e%s&c.";
    SimpleMessage CMD_CLEAR_SELF = () -> "&aYour inventory has been cleared!";
    SimpleMessage CMD_HEAL_SELF = () -> "&aYour health, hunger and potion effects have been restored!";
    SimpleMessage CMD_CLEARCHAT = () -> "&c"; // Empty String
    SimpleMessage CMD_FEED_SELF = () -> "&aYour hunger has been restored!";
    SimpleMessage CMD_OPERATOR_SELF_ENABLED = () -> "&aYou have been given operator permissions!";
    SimpleMessage CMD_OPERATOR_SELF_DISABLED = () -> "&cYou have been revoked operator permissions!";
    SimpleMessage CMD_SELF_GIVE_USAGE = () -> "&cUsage: &8/&7i &8[&bitem&8] &8[&damount&8]";
    SimpleMessage INVALID_MATERIAL = () -> "&cInvalid material! Please try again.";
    SimpleMessage CMD_SELF_GIVE_SUCCESS = () -> "&aYou received &ax&e%s &aof &e%s&a!";
    SimpleMessage INVALID_AMOUNT = () -> "&cInvalid number! Please try again.";
    SimpleMessage CMD_SELF_GIVE_NONE = () -> "&cYou cannot give yourself &e0 &cof an item!";
    SimpleMessage CMD_SELF_GIVE_AIR = () -> "&cYou cannot give yourself &eAIR!";
    SimpleMessage CMD_SPEED_SELF = () -> "&aYour flight speed has been set to &e%s&a!";
    SimpleMessage CMD_UP_USAGE = () -> "&cUsage: &8/&7up &8[&damount&8]";
    SimpleMessage CMD_UP_SUCCESS = () -> "&aYou have been teleported &e%s &ablocks up!";
    SimpleMessage CMD_TOP_SUCCESS = () -> "&aYou have been teleported to the &eTOP OF THE WORLD&a!";
    SimpleMessage CMD_BACK_NO_LAST_LOCATION = () -> "&cYou do not have a last location to teleport to!";
    SimpleMessage CMD_BACK_SUCCESS = () -> "&aYou have been teleported to &eYOUR LAST LOCATION&a!";
}
