package in.techpro424.itemblacklist.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.text.Text;

public class AddIdToBlacklistCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, RegistrationEnvironment environment) {
        
        dispatcher.register(CommandManager.literal("addIdToBlacklist").requires(source -> source.hasPermissionLevel(4))
        .then(CommandManager.argument("id", IdentifierArgumentType.identifier())
        .executes(AddIdToBlacklistCommand::run)));
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        String id = IdentifierArgumentType.getIdentifier(context, "id").toString();

        Config.addIdToConfig(id);

        context.getSource().sendFeedback(() -> Text.literal("Added §b" + id + "§r to the blacklist."), true);
        return 1;
    }
}
