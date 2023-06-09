package in.techpro424.itemblacklist.commands;

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
        
        dispatcher.register(CommandManager.literal("listIdsInBlacklist")
        .executes(ListIdsInBlacklistCommand::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        String idsInConfig = Config.listConfig();

        context.getSource().sendFeedback(() -> Text.literal(idsInConfig), true);
        return 1;
    }
}
