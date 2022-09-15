package me.Gabo6480.relevalexpansion;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelEvalExpansion extends PlaceholderExpansion implements Relational {

    static final Pattern placeholderPattern = Pattern.compile("(player|viewed):");

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
        return "1.1";
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

        if(player == null || viewed == null)
            return null;

        List<MatchResult> resultList = placeholderPattern.matcher(params).results().toList();

        //The most basic variant of the placeholder will at least match once
        //If there are none, then something is wrong
        if(resultList.isEmpty())
            return null;

        //args[1] should either be {bracketed-placeholder} or {bracketed-placeholder}_<player/viewed>
        //regardless PlaceholderAPI.setBracketPlaceholders will evaluate them both without problems
        String result = EvaluateAs(
                resultList.get(0).group(1),
                player, viewed,
                params.substring(
                        resultList.get(0).end(1) + 1,
                        resultList.size() > 1
                                ? resultList.get(1).start(1) - 1
                                : params.length()));

        if(result != null && resultList.size() > 1){
            String yesno = result;
            if(yesno.equals(PlaceholderAPIPlugin.booleanTrue())) {
                result = EvaluateAs(
                        resultList.get(1).group(1),
                        player, viewed,
                        params.substring(
                                resultList.get(1).end(1) + 1,
                                resultList.size() > 2
                                        ? resultList.get(2).start(1) - 1
                                        : params.length()));
            }
            else if(yesno.equals(PlaceholderAPIPlugin.booleanFalse())) {
                    result = resultList.size() > 2 ?
                            EvaluateAs( resultList.get(2).group(1),
                                    player, viewed,
                                    params.substring(
                                            resultList.get(2).end(1) + 1
                                    ))
                            : "";
            }else{
                    return getIdentifier() + "_invalid_input:" + yesno;
            }
        }

        return result;
    }

}
