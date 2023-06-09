package in.techpro424.itemblacklist.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.altconfig.Config;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.text.Text;

public class RemoveIdFromBlacklistCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, RegistrationEnvironment environment) {
        
        dispatcher.register(CommandManager.literal("removeIdFromBlacklist")
        .then(CommandManager.argument("id", IdentifierArgumentType.identifier())
        .executes(RemoveIdFromBlacklistCommand::run)));
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        String id = IdentifierArgumentType.getIdentifier(context, "id").toString();

        Config.removeIdFromConfig(id);

        context.getSource().sendFeedback(Text.literal("Removed §b" + id + "§r from the blacklist."), true);
        return 1;
    }
}
