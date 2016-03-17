package de.alpha.uhc.scoreboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.alpha.border.Border;
import de.alpha.uhc.Core;
import de.alpha.uhc.Listener.LobbyListener;
import de.alpha.uhc.files.SpawnFileManager;
import de.alpha.uhc.teams.ATeam;
import de.alpha.uhc.utils.Stats;

public class AScoreboard {
	
	public static boolean ShowLobbyScoreboard;
	public static boolean ShowLobbyKills;
	public static boolean ShowLobbyDeaths;
	public static boolean ShowLobbyCoins;
	public static boolean ShowLobbyPlayercount;
	public static boolean ShowLobbyTeam;
	public static boolean ShowLobbyKit;
	public static boolean ShowLobbyBar;
	
	public static String lobbyTitle;
	public static String lobbyKills;
	public static String lobbyDeaths;
	public static String lobbyCoins;
	public static String lobbyPlayercount;
	public static String lobbyTeam;
	public static String lobbyKit;
	public static String lobbyBar;
	
	public static boolean ShowInGameScoreboard;
	public static boolean ShowInGamePlayersLiving;
	public static boolean ShowInGameSpectators;
	public static boolean ShowInGameKit;
	public static boolean ShowInGameCenter;
	public static boolean ShowInGameTeam;
	public static boolean ShowInGameBorder;
	public static boolean ShowInGameBar;
	
	public static String ingameTitle;
	public static String ingamePlayersLiving;
	public static String ingameSpectators;
	public static String ingameKit;
	public static String ingameCenter;
	public static String ingameTeam;
	public static String ingameBorder;
	public static String ingameBar;
	
	public static boolean ShowHealthUName;
	public static boolean ShowHealthInTab;
	
	private static HashMap<Player, String> A = new HashMap<Player, String>();
	private static HashMap<Player, String> B = new HashMap<Player, String>();
	
	private static int aa;
	private static int bb;
	
	private static Scoreboard sb;
	private static Objective obj;
	
	public static void setLobbyScoreboard(Player p) {
		if(ShowLobbyScoreboard == false) return;
		
		int score = 0;
		
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
		obj = sb.registerNewObjective("UHCLobby", "dummy");
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(lobbyTitle);
		
		if(ShowLobbyBar) {
			obj.getScore(lobbyBar).setScore(score);
			score--;
		}
		
		if(ShowLobbyKills) {
			String a = lobbyKills.replace("[kills]", ""+new Stats(p).getKills());
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowLobbyDeaths) {
			String a = lobbyDeaths.replace("[deaths]", ""+new Stats(p).getDeaths());
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowLobbyCoins) {
			String a = lobbyCoins.replace("[coins]", ""+new Stats(p).getCoins());
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowLobbyBar) {
			obj.getScore("§a"+lobbyBar).setScore(score);
			score--;
		}
		
		if(ShowLobbyPlayercount) {
			String a = lobbyPlayercount.replace("[playercount]", Integer.toString(Bukkit.getOnlinePlayers().size()));
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowLobbyBar) {
			obj.getScore("§b"+lobbyBar).setScore(score);
			score--;
		}
		
		if(ShowLobbyTeam) {
			String a = lobbyTeam.replace("[team]", ""+ATeam.getTeamColor(ATeam.getPlayerTeam(p))+ATeam.getPlayerTeam(p));
			obj.getScore(a).setScore(score);
			A.put(p, a);
			aa = score;
			score--;
		}
		
		if(ShowLobbyKit) {
			String a = lobbyKit.replace("[kit]", ""+LobbyListener.getSelKit(p));
			obj.getScore(a).setScore(score);
			B.put(p, a);
			bb = score;
			score--;
		}
		
		p.setScoreboard(sb);
	}
	
	public static void updateLobbyTeam(Player p) {
		Objective objP = p.getScoreboard().getObjective("UHCLobby");
		String a = lobbyTeam.replace("[team]", ""+ATeam.getTeamColor(ATeam.getPlayerTeam(p))+ATeam.getPlayerTeam(p));
		p.getScoreboard().resetScores(A.get(p));
		A.put(p, a);
		objP.getScore(a).setScore(aa);
	}
	
	public static void updateLobbyKit(Player p) {
		Objective objP = p.getScoreboard().getObjective("UHCLobby");
		String a = lobbyKit.replace("[kit]", ""+LobbyListener.getSelKit(p));
		p.getScoreboard().resetScores(B.get(p));
		B.put(p, a);
		objP.getScore(a).setScore(bb);
	}
	
	private static HashMap<Player, String> lA = new HashMap<Player, String>();
	private static HashMap<Player, String> lB = new HashMap<Player, String>();
	private static HashMap<Player, String> lE = new HashMap<Player, String>();
	private static HashMap<Player, String> lF = new HashMap<Player, String>();
	
	private static int la;
	private static int lb;
	private static int le;
	private static int lf;
	
	public static void setInGameScoreboard(final Player p) {
		if(ShowInGameScoreboard == false) return;
		
		int score = 0;
		
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
		obj = sb.registerNewObjective("UHCInGame", "dummy");
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ingameTitle);
		
		if(ShowInGameBar) {
			obj.getScore(ingameBar).setScore(score);
			score--;
		}
		
		if(ShowInGamePlayersLiving) {
			String a = ingamePlayersLiving.replace("[livingPlayerscount]", Integer.toString(Core.getInGamePlayers().size()));
			lA.put(p, a);
			la = score;
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowInGameSpectators) {
			String a = ingameSpectators.replace("[spectatorcount]", Integer.toString(Core.getSpecs().size()));
			lB.put(p, a);
			lb = score;
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowInGameBar) {
			obj.getScore("§a"+ingameBar).setScore(score);
			score--;
		}
		
		if(ShowInGameKit) {
			String a = ingameKit.replace("[kit]", LobbyListener.getSelKit(p));
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowInGameTeam) {
			String a = ingameTeam.replace("[team]", ""+ATeam.getTeamColor(ATeam.getPlayerTeam(p))+ ATeam.getPlayerTeam(p));
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowInGameBar) {
			obj.getScore("§b"+ingameBar).setScore(score);
			score--;
		}
		
		if(ShowInGameCenter) {
			String a = ingameCenter.replace("[distanceToCenter]", Integer.toString((int) p.getLocation().distance(Border.arena)));
			lE.put(p, a);
			le = score;
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowInGameBorder) {
			String a = ingameBorder.replace("[bordersize]", Integer.toString(Border.size));
			lF.put(p, a);
			lf = score;
			obj.getScore(a).setScore(score);
			score--;
		}
		
		if(ShowHealthUName) {
			Objective objName = sb.registerNewObjective("UHCHealthName", "health");
			objName.setDisplaySlot(DisplaySlot.BELOW_NAME);
			objName.setDisplayName("❤");
		}
		
		if(ShowHealthInTab) {
			Objective objName = sb.registerNewObjective("UHCHealthTab", "health");
			objName.setDisplaySlot(DisplaySlot.PLAYER_LIST);
			objName.setDisplayName("❤");
		}
		
		p.setScoreboard(sb);
		for(final Player online : Bukkit.getOnlinePlayers()){
			  new BukkitRunnable() {
				@Override
				public void run() {
					online.damage(1);
					online.setHealth(online.getHealth()+1);
				}
			}.runTaskLater(Core.getInstance(), 20);
		}
	}
	
	public static void updateInGamePlayersLiving(Player p) {
		Objective objP = p.getScoreboard().getObjective("UHCInGame");
		String a = ingamePlayersLiving.replace("[livingPlayerscount]", Integer.toString(Core.getInGamePlayers().size()));
		p.getScoreboard().resetScores(lA.get(p));
		lA.put(p, a);
		objP.getScore(a).setScore(la);
	}
	
	public static void updateInGameSpectators(Player p) {
		Objective objP = p.getScoreboard().getObjective("UHCInGame");
		String a = ingameSpectators.replace("[spectatorcount]", Integer.toString(Core.getSpecs().size()));
		p.getScoreboard().resetScores(lB.get(p));
		lB.put(p, a);
		objP.getScore(a).setScore(lb);
	}
	
	public static void updateInGameCenter(Player p) {
		Objective objP = p.getScoreboard().getObjective("UHCInGame");
		if(SpawnFileManager.getSpawn() == null) {
			String  a = ingameCenter.replace("[distanceToCenter]", Integer.toString((int) p.getLocation().distance(SpawnFileManager.getSpawn().getWorld().getHighestBlockAt(SpawnFileManager.getSpawn().getBlockX(), SpawnFileManager.getSpawn().getBlockZ()).getLocation())));
			p.getScoreboard().resetScores(lE.get(p));
			lE.put(p, a);
			objP.getScore(a).setScore(le);
		} else {
			String a = ingameCenter.replace("[distanceToCenter]", Integer.toString((int) p.getLocation().distance(Border.arena.getWorld().getHighestBlockAt(Border.arena).getLocation())));
			p.getScoreboard().resetScores(lE.get(p));
			lE.put(p, a);
			objP.getScore(a).setScore(le);
		}
	}
	
	public static void updateInGameBorder(Player p) {
		Objective objP = p.getScoreboard().getObjective("UHCInGame");
		String a = ingameBorder.replace("[bordersize]", Integer.toString(Border.size));
		p.getScoreboard().resetScores(lF.get(p));
		lF.put(p, a);
		objP.getScore(a).setScore(lf);
	}
	
	
}
