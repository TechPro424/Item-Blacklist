package in.techpro424.itemblacklist.commands;

import com.google.common.collect.Iterables;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.text.Text;
import net.minecraft.server.command.ServerCommandSource;

public class ListIdsInBlacklistCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, RegistrationEnvironment environment) {
        

        dispatcher.register(CommandManager.literal("listIdsInBlacklist").requires(source -> source.hasPermissionLevel(4))
        .then(CommandManager.literal("global").executes(ListIdsInBlacklistCommand::run))
        .then(CommandManager.literal("overworld").executes(ListIdsInBlacklistCommand::run))
        .then(CommandManager.literal("nether").executes(ListIdsInBlacklistCommand::run))
        .then(CommandManager.literal("end").executes(ListIdsInBlacklistCommand::run))
        );
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        String blacklist = Iterables.getLast(context.getNodes()).getNode().getName();
        String idsInConfig = Config.listConfig(blacklist);

        context.getSource().sendFeedback(() -> Text.literal(idsInConfig), true);
        return 1;
    }
}
