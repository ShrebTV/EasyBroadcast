package shreb.me.easybroadcast;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BroadcastCommands implements TabExecutor {

    Configuration config = EasyBroadcast.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) return false;

//fadeIn
        if (args[0].equalsIgnoreCase("fadeIn")) {

            if (!sender.hasPermission("EB.editConfig")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command.");
                return true;
            }

            if (args.length == 1) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "The fadeIn timing is set to " + config.getInt("editableByCommand.fadeIn") + ChatColor.LIGHT_PURPLE + " ticks");
                return true;
            }

            int fadeIn = -1;
            try {
                fadeIn = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "EB: the second argument of the command was not a number");
            }

            if (fadeIn < 0) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "EB: You cannot set the fadeIn time to a negative value!");
                return true;
            } else {
                config.set("editableByCommand.fadeIn", fadeIn);
                EasyBroadcast.getInstance().saveConfig();
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "the fadein time has been set to " + fadeIn + ChatColor.LIGHT_PURPLE + " ticks");
            }

            return true;

        }
//stay
        else if (args[0].equalsIgnoreCase("stay")) {

            if (!sender.hasPermission("EB.editConfig")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command.");
                return true;
            }

            if (args.length == 1) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "The stay timing is set to " + config.getInt("editableByCommand.stay") + ChatColor.LIGHT_PURPLE + " ticks");
                return true;
            }

            int stay = -1;
            try {
                stay = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "EB: the second argument of the command was not a number");

            }

            if (stay < 0) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "EB: You cannot set the stay time to a negative value!");
                return true;
            } else {
                config.set("editableByCommand.stay", stay);
                EasyBroadcast.getInstance().saveConfig();
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "the stay time has been set to " + stay + ChatColor.LIGHT_PURPLE + " ticks");
            }

            return true;

        }
//fadeout
        else if (args[0].equalsIgnoreCase("fadeOut")) {

            if (!sender.hasPermission("EB.editConfig")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command.");
                return true;
            }

            if (args.length == 1) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "The fadeOut timing is set to " + config.getInt("editableByCommand.fadeOut") + ChatColor.LIGHT_PURPLE + " ticks");
                return true;
            }

            int fadeOut = -1;
            try {
                fadeOut = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "EB: the second argument of the command was not a number");

            }

            if (fadeOut < 0) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "EB: You cannot set the fadeOut time to a negative value!");
                return true;
            } else {
                config.set("editableByCommand.fadeOut", fadeOut);
                EasyBroadcast.getInstance().saveConfig();
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "the fadeout time has been set to " + fadeOut + ChatColor.LIGHT_PURPLE + " ticks");
            }

            return true;

        }
//send
        else if (args[0].equalsIgnoreCase("send")) {

            if (!sender.hasPermission("EB.sendBC")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command.");
                return true;
            }

            StringBuilder builder = new StringBuilder();

            args[0] = "";

            for (String s : args) {
                builder.append(s);
                builder.append(" ");
            }

            for (Player p : EasyBroadcast.getInstance().getServer().getOnlinePlayers()) {
                p.sendTitle(builder.toString(), config.getString("editableByCommand.subtitleMessage"), config.getInt("editableByCommand.fadeIn"), config.getInt("editableByCommand.stay"), config.getInt("editableByCommand.fadeOut"));
            }

            return true;
        }

//Subtitle
        else if (args[0].equalsIgnoreCase("subtitle")) {

            if (!sender.hasPermission("EB.editConfig")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command.");
                return true;
            }

            if (args.length == 1) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "The current subtitle is \"" + config.getString("editableByCommand.subtitleMessage") + "\"");
                return true;
            }

            StringBuilder subtitle = new StringBuilder();

            args[0] = "";

            for (String s : args) {
                subtitle.append(s);
                subtitle.append(" ");
            }

            config.set("editableByCommand.subtitleMessage", subtitle.toString());
            EasyBroadcast.getInstance().saveConfig();

            if (Objects.equals(config.getString("editableByCommand.subtitleMessage"), subtitle.toString())) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "The subtitle has been changed successfully");
            } else {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Error saving the subtitle to the config. Please let the plugins author know that this happened and what command you were running.");
            }
            return true;
        }
// cancel
        else if(args[0].equalsIgnoreCase("cancel")){

            if(args.length > 1 && args[1].equalsIgnoreCase("all") && sender.hasPermission("EB.cancelTitleAll")){
                for(Player p:EasyBroadcast.getInstance().getServer().getOnlinePlayers()){
                    p.resetTitle();
                }

                sender.sendMessage(ChatColor.LIGHT_PURPLE + "All titles have been cancelled!");
                return true;
            }

            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "This command can only be used by Players");
                return true;
            }

            if(sender.hasPermission("EB.cancelTitleSelf")){
                ((Player)sender).resetTitle();
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Your displayed title has been reset");
            } else{
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
            }
            return true;
        }
//disableTitles
        if(args[0].equalsIgnoreCase("switchMode")){
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "This command can only be used by Players");
                return true;
            }

            if(((Player)sender).getScoreboardTags().contains("EasyBroadcastTitleDisabled")){
                ((Player)sender).getScoreboardTags().remove("EasyBroadcastTitleDisabled");
                sender.sendMessage(ChatColor.DARK_PURPLE + "Titles have been enabled");
            } else {
                ((Player)sender).getScoreboardTags().add("EasyBroadcastTitleDisabled");
                sender.sendMessage(ChatColor.DARK_PURPLE + "Titles have been disabled");
            }
            return true;

        }


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {

            ArrayList<String> tab0 = new ArrayList<>();

            tab0.add("switchMode");

            if (sender.hasPermission("EB.sendBC")) {
                tab0.add("send");
            }

            if (sender.hasPermission("EB.editConfig")) {
                tab0.add("fadeIn");
                tab0.add("stay");
                tab0.add("fadeOut");
                tab0.add("subtitle");
            }

            if(sender.hasPermission("EB.cancelTitleSelf")){
                tab0.add("cancel");
            }

            return tab0;
        }

        if ((args[0].equalsIgnoreCase("fadeIn") ||
                args[0].equalsIgnoreCase("stay") ||
                args[0].equalsIgnoreCase("fadeOut")) &&
                args.length == 2 &&
                sender.hasPermission("EB.editConfig")) {

            ArrayList<String> tab1 = new ArrayList<>();

            if (sender.hasPermission("EB.editConfig")) {
                tab1.add("20");
                tab1.add("40");
                tab1.add("50");
            }
            return tab1;
        }

        if(args[0].equalsIgnoreCase("cancel")){
            ArrayList<String> tab1 = new ArrayList<>();

            if(sender.hasPermission("EB.cancelTitleAll")){
                tab1.add("all");
            }

            return tab1;
        }

        return null;
    }
}
