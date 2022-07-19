package me.Gabo6480.relevalexpansion;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RelEvalExpansion extends PlaceholderExpansion implements Relational {

    @Override
    public @NotNull String getIdentifier() {
        return "releval";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Gabo6480";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    private String EvaluateAs(String selector, Player player, Player viewed, String params){
        return switch (selector) {
            case "player" -> PlaceholderAPI.setBracketPlaceholders(player, params);
            case "viewed" -> PlaceholderAPI.setBracketPlaceholders(viewed, params);
            default -> null;
        };
    }

    public String onPlaceholderRequest(Player player, Player viewed, String params) {
        //Possible structures of the placeholder
        //%rel_releval_<player/viewed>:<bracketed-placeholder>%
        //%rel_releval_<player/viewed>:<bracketed-placeholder-returning-yes/no>_<player/viewed>:<result-yes>%
        //%rel_releval_<player/viewed>:<bracketed-placeholder-returning-yes/no>_<player/viewed>:<result-yes>_<player/viewed>:<result-no>%


        String[] args = params.split(":");
        //The most basic variant of the placeholder has at least 2 args divided by :
        //If there are any less, then something is wrong
        if(args.length < 2)
            return null;

        //args[1] should either be {bracketed-placeholder} or {bracketed-placeholder}_<player/viewed>
        //regardless PlaceholderAPI.setBracketPlaceholders will evaluate them both without problems
        String result = EvaluateAs(args[0], player, viewed, args[1]);

        if(args.length > 2 && result != null){
            //Remove the last 7 chars as they will contain _<player/viewed>
            String yesno = result.substring( 0, result.length() - 7);
            //Isolate the last 6 chars, as they will contain <player/viewed>
            String evalAs = result.substring(result.length() - 6);
            switch (yesno){
                case "yes":
                    result = EvaluateAs(evalAs, player, viewed, args[2]);
                    if(result != null)
                        result = result.substring( 0, result.length() - 7);
                    break;
                case "no":
                    result = args.length > 3 ?
                            EvaluateAs(args[2].substring(args[2].length() - 6), player, viewed, args[3])
                            : null;
                    break;
                default:
                    return getIdentifier() + "_invalid_input:" + yesno;
            }
        }

        return result;
    }

}
