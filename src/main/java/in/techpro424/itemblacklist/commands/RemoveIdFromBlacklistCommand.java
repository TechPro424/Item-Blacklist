package in.techpro424.itemblacklist.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.network.chat.Component;

public class RemoveIdFromBlacklistCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext access, CommandSelection environment) {

        dispatcher.register(Commands.literal("removeItemFromBlacklist").requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
        .then(Commands.argument("id", IdentifierArgument.id()).suggests(new BlacklistSuggestionsProvider()).executes(RemoveIdFromBlacklistCommand::run)
        ));
    }



    public static int run(CommandContext<CommandSourceStack> context) {
        String id = IdentifierArgument.getId(context, "id").toString();
        if (!Config.getBlacklist().contains(id)) {
            context.getSource().sendSuccess(() -> Component.literal("§b" + id + "§r is already absent from the blacklist."), true);
        }
        else {
            Config.removeIdFromConfig(id);
            context.getSource().sendSuccess(() -> Component.literal("Removed §b" + id + "§r from the blacklist."), true);
        }


        return 1;
    }
}
