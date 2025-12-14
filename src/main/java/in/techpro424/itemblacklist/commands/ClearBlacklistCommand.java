package in.techpro424.itemblacklist.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.network.chat.Component;

public class ClearBlacklistCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext access, CommandSelection environment) {

        dispatcher.register(Commands.literal("clearBlacklist").requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_ADMIN)).executes(ClearBlacklistCommand::run)
        );
    }

    public static int run(CommandContext<CommandSourceStack> context) {
        Config.clearBlacklist();
        context.getSource().sendSuccess(() -> Component.literal("Cleared the blacklist."), true);
        return 1;
    }
}
