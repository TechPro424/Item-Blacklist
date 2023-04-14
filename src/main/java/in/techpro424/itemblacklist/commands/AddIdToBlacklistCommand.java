package in.techpro424.itemblacklist.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.ItemBlacklist;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.text.Text;

public class AddIdToBlacklistCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, RegistrationEnvironment environment) {
        
        dispatcher.register(CommandManager.literal("addIdToBlacklist")
        .then(CommandManager.argument("id", StringArgumentType.greedyString())
        .executes(AddIdToBlacklistCommand::run)));
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        String id = StringArgumentType.getString(context, "id");

        ItemBlacklist.CONFIG.addIdToConfig(id);

        context.getSource().sendFeedback(Text.literal("Added §b" + id + "§r to the blacklist."), true);
        return 1;
    }
}
