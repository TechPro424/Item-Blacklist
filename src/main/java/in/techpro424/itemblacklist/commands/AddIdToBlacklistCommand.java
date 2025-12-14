package in.techpro424.itemblacklist.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.item.Item;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.network.chat.Component;

public class AddIdToBlacklistCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext access, CommandSelection environment) {
        
        dispatcher.register(Commands.literal("addItemToBlacklist").requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
        .then(Commands.argument("id", ItemArgument.item(access)).executes(AddIdToBlacklistCommand::run)
        ));

        
    
    }

    public static int run(CommandContext<CommandSourceStack> context) {
        Item item = ItemArgument.getItem(context, "id").getItem();
        String id = Id.getIdFromItem(item);
        if (Config.getBlacklist().contains(id)) {
            context.getSource().sendSuccess(() -> Component.literal("§b" + id + "§r is already present in the blacklist."), true);
        }
        else {
            Config.addIdToConfig(id);
            context.getSource().sendSuccess(() -> Component.literal("Added §b" + id + "§r to the blacklist."), true);
        }

        return 1;
    }
}
