package in.techpro424.itemblacklist.util;

import in.techpro424.itemblacklist.commands.AddIdToBlacklistCommand;
import in.techpro424.itemblacklist.commands.ClearBlacklistCommand;
import in.techpro424.itemblacklist.commands.ListIdsInBlacklistCommand;
import in.techpro424.itemblacklist.commands.RemoveIdFromBlacklistCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommandRegister {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(AddIdToBlacklistCommand::register);
        CommandRegistrationCallback.EVENT.register(RemoveIdFromBlacklistCommand::register);
        CommandRegistrationCallback.EVENT.register(ListIdsInBlacklistCommand::register);
        CommandRegistrationCallback.EVENT.register(ClearBlacklistCommand::register);
    }
}
