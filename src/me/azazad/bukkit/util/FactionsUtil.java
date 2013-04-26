package me.azazad.bukkit.util;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.struct.Relation;

import org.bukkit.entity.Player;

public class FactionsUtil {
	
	public static Boolean factionsCheck(Player attack, Player defend) {// isHostile
        FPlayer attacker = (FPlayer) FPlayers.i.get((Player) attack);
        FPlayer defender = (FPlayer) FPlayers.i.get((Player) defend);
        if (attacker != null && defender != null) {
            Faction defendFaction = defender.getFaction();
            Faction attackFaction = attacker.getFaction();
            if (attackFaction.isNone()) { // owner is not in a faction.
                if (Conf.disablePVPForFactionlessPlayers) {
                    return false;
                } else {
                    return true;
                }
            }
            if (defendFaction.isNone()) { // owner is not in a faction.
                if (Conf.disablePVPForFactionlessPlayers) {
                    return false;
                } else {
                    return true;
                }
            }
            // both have factions - test relationship
            Relation relation = defendFaction.getRelationTo(attackFaction);
            switch (relation) {
                //case LEADER:
                //case OFFICER:
                case MEMBER:
                case ALLY:
                //case TRUCE:
                //  return false;
                case NEUTRAL:
                case ENEMY:
                    return true;
                default:
                    return true;
            }
        }
        return true;
    }
	
}
