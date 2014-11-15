package co.forsaken.hqm;

import hardcorequesting.QuestingData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import cpw.mods.fml.common.FMLLog;

public class ForsakenCommandHandler extends CommandBase {

  @Override public String getCommandName() {
    return "quest";
  }

  @Override public String getCommandUsage(ICommandSender sender) {
    return "/" + getCommandName() + " help";
  }

  @Override public void processCommand(ICommandSender sender, String[] arguments) {
    if ((arguments[0].matches("reset"))) {
      if (sender instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer) sender;
        if (arguments.length > 1 && !arguments[1].matches("confirm") && isOp(sender)) {
          String name = arguments.length == 2 ? arguments[1] : QuestingData.getUserName(player);
          if (QuestingData.hasData(name)) {
            QuestingData.getQuestingData(name).getTeam().clearProgress();
            player.sendChatToPlayer(ChatMessageComponent.createFromText(name + "'s data has been reset"));
          } else {
            player.sendChatToPlayer(ChatMessageComponent.createFromText("That player does not exist."));
          }
        } else if (arguments.length == 2 && arguments[1].matches("confirm")) {
          QuestingData.getQuestingData(player).getTeam().clearProgress();
          player.sendChatToPlayer(ChatMessageComponent.createFromText("Your questing data has been reset!"));
        } else {
          player.sendChatToPlayer(ChatMessageComponent.createFromText("To fully reset your questing data, please type /" + getCommandName() + " reset confirm"));
        }
        return;
      } else {
        if (arguments.length > 1) {
          String name = arguments[1];
          if (QuestingData.hasData(name)) {
            QuestingData.getQuestingData(name).getTeam().clearProgress();
            FMLLog.info(name + "'s questing data has been reset");
          } else {
            FMLLog.info(name + " cannot be found.");
          }
        }
        return;
      }
    }
  }

  public boolean isOp(ICommandSender sender) {
    if ((sender instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer) sender;
      String username = QuestingData.getUserName(player);
      return MinecraftServer.getServer().getConfigurationManager().isPlayerOpped(username);
    }
    return true;
  }

  @Override public int compareTo(Object obj) {
    if ((obj instanceof ICommand)) { return compareTo((ICommand) obj); }
    return 0;
  }

}
