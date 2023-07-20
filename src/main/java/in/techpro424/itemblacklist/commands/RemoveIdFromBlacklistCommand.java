package in.techpro424.itemblacklist.commands;

import com.google.common.collect.Iterables;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.text.Text;

public class RemoveIdFromBlacklistCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, RegistrationEnvironment environment) {

        dispatcher.register(CommandManager.literal("removeIdToBlacklist").requires(source -> source.hasPermissionLevel(4))
        .then(CommandManager.argument("id", IdentifierArgumentType.identifier())
        .then(CommandManager.literal("global").executes(RemoveIdFromBlacklistCommand::run))
        .then(CommandManager.literal("overworld").executes(RemoveIdFromBlacklistCommand::run))
        .then(CommandManager.literal("nether").executes(RemoveIdFromBlacklistCommand::run))
        .then(CommandManager.literal("end").executes(RemoveIdFromBlacklistCommand::run))
        ));
    }



    public static int run(CommandContext<ServerCommandSource> context) {
        String id = IdentifierArgumentType.getIdentifier(context, "id").toString();
        String blacklist = Iterables.getLast(context.getNodes()).getNode().getName();

        Config.removeIdFromConfig(id, blacklist);

        context.getSource().sendFeedback(() -> Text.literal("Removed §b" + id + "§r from the \u00A7l" +blacklist+ "§r blacklist."), true);
        return 1;
    }
}
