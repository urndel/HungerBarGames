package me.tomjw64.HungerBarGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.tomjw64.HungerBarGames.Commands.HBGCommand;
import me.tomjw64.HungerBarGames.Commands.GenCommands.*;
import me.tomjw64.HungerBarGames.Commands.ModCommands.*;
import me.tomjw64.HungerBarGames.Managers.ConfigManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandHandler {
	private static final ChatColor RED=ChatColor.RED;
	private static List<HBGCommand> cmds=new ArrayList<HBGCommand>();
	private static Map<CommandSender,Arena> selections=new HashMap<CommandSender,Arena>();
	
	public static void loadCommands()
	{
		cmds.add(new Help());
		cmds.add(new ListArenas());
		cmds.add(new ListClasses());
		cmds.add(new ListPlaylists());
		cmds.add(new Join());
		cmds.add(new Leave());
		cmds.add(new Tributes());
		cmds.add(new Spec());
		cmds.add(new AssignChest());
		cmds.add(new Unassign());
		cmds.add(new AutoAssign());
		cmds.add(new Create());
		cmds.add(new Delete());
		cmds.add(new Reload());
		cmds.add(new Select());
		cmds.add(new SetBoundary());
		cmds.add(new SetLobby());
		cmds.add(new SetMin());
		cmds.add(new SetMax());
		cmds.add(new SetSpawn());
		cmds.add(new SetSpec());
		cmds.add(new Start());
		cmds.add(new Play());
		cmds.add(new Stop());
	}
	public static void handleCommand(HungerBarGames pl,CommandSender sender, String[] args)
	{
		//The command sent to the plugin
		String cmd=args[0];
		//New arguments
		String[] subArgs=new String[0];
		if(args.length!=0)
		{
			subArgs=new String[args.length-1];
			System.arraycopy(args,1,subArgs,0,subArgs.length);
		}
		//Check for the command
		for(HBGCommand c:cmds)
		{
			if(cmd.equalsIgnoreCase(c.cmd()))
			{
				if(subArgs.length>=c.numArgs())
				{
					String perm=c.permission();
					String[] permSplit=perm.split("\\.");
					String permGroup="";
					for(int x=0;x<permSplit.length-1;x++)
					{
						permGroup+=permSplit[x]+".";
					}
					permGroup+="*";
					if(sender.isOp()||sender.hasPermission(perm)
							||sender.hasPermission(permGroup)
							||sender.hasPermission("HBG.*")
							||(!ConfigManager.usingPlayerPerms()&&permSplit[1].equalsIgnoreCase("player")))
					{
						c.execute(sender,subArgs);
					}
					else
					{
						sender.sendMessage(RED+"Insufficient permissions!");
					}
				}
				else
				{
					sender.sendMessage(RED+"Incompatible arguments!");
				}
				return;
			}
		}
		sender.sendMessage(RED+"Not a valid command!");
	}
	public static Map<CommandSender,Arena> getSelections()
	{
		return selections;
	}
	public static List<HBGCommand> getCmds()
	{
		return cmds;
	}
}