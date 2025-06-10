package org.brassbrewery.fragaliciousCore.util;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.brassbrewery.fragaliciousCore.exceptions.UnknownPlayerException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtil {

    public static void sendMessage(Player player, String message) {
        player.sendMessage(convertStringToComponenet(message));
    }
    public static void sendMessages(UUID playerId, String message) throws UnknownPlayerException {
        Player player = Bukkit.getPlayer(playerId);
        if(player == null){
            throw new UnknownPlayerException(playerId);
        }else{
            sendMessage(player,message);
        }
    }
    public static void sendMessages(String playerName, String message) throws UnknownPlayerException {
        Player player = Bukkit.getPlayerExact(playerName);
        if(player == null){
            throw new UnknownPlayerException(playerName);
        }else{
            sendMessage(player,message);
        }
    }

    public static void broadcastMessage(String message){
        Bukkit.getServer().broadcast(convertStringToComponenet(message));
    }
    public static void sendActionBarMessage(Player player,String message){
        player.sendActionBar(convertStringToComponenet(message));
    }
    public static void sendActionBarMessage(UUID playerId, String message) throws UnknownPlayerException {
        Player player = Bukkit.getPlayer(playerId);
        if(player == null){
            throw new UnknownPlayerException(playerId);
        }else{
            sendMessage(player,message);
        }
    }
    public static void sendActionBarMessage(String playerName, String message) throws UnknownPlayerException {
        Player player = Bukkit.getPlayerExact(playerName);
        if(player == null){
            throw new UnknownPlayerException(playerName);
        }else{
            sendMessage(player,message);
        }
    }
    public static void sendTitleMessage(Player player,String title, String subtitle, int fadeIn, int fadeOut, int time){
        TextComponent titleComponent =  convertStringToComponenet(title);
        TextComponent subtitleComponent =  convertStringToComponenet(subtitle);
        Title.Times times = Title.Times.times(Duration.ofSeconds(fadeIn), Duration.ofSeconds(time), Duration.ofSeconds(fadeOut));
        player.showTitle(Title.title(titleComponent, subtitleComponent, times));
    }
    public static TextComponent convertStringToComponenet(String message) {
        TextComponent.Builder builder = Component.text();

        Pattern pattern = Pattern.compile("(?i)(#[a-f0-9]{6}|&[0-9a-fk-or])");
        Matcher matcher = pattern.matcher(message);

        int lastIndex = 0;
        TextColor currentColor = null;
        boolean bold = false, italic = false, underline = false, strikethrough = false, obfuscated = false;

        while (matcher.find()) {
            // Append text before match
            if (matcher.start() > lastIndex) {
                String text = message.substring(lastIndex, matcher.start());
                builder.append(Component.text(text)
                        .color(currentColor)
                        .decoration(TextDecoration.BOLD, bold)
                        .decoration(TextDecoration.ITALIC, italic)
                        .decoration(TextDecoration.UNDERLINED, underline)
                        .decoration(TextDecoration.STRIKETHROUGH, strikethrough)
                        .decoration(TextDecoration.OBFUSCATED, obfuscated));
            }

            String code = matcher.group();
            if (code.startsWith("&")) {
                char c = Character.toLowerCase(code.charAt(1));
                switch (c) {
                    case '0': currentColor = NamedTextColor.BLACK; break;
                    case '1': currentColor = NamedTextColor.DARK_BLUE; break;
                    case '2': currentColor = NamedTextColor.DARK_GREEN; break;
                    case '3': currentColor = NamedTextColor.DARK_AQUA; break;
                    case '4': currentColor = NamedTextColor.DARK_RED; break;
                    case '5': currentColor = NamedTextColor.DARK_PURPLE; break;
                    case '6': currentColor = NamedTextColor.GOLD; break;
                    case '7': currentColor = NamedTextColor.GRAY; break;
                    case '8': currentColor = NamedTextColor.DARK_GRAY; break;
                    case '9': currentColor = NamedTextColor.BLUE; break;
                    case 'a': currentColor = NamedTextColor.GREEN; break;
                    case 'b': currentColor = NamedTextColor.AQUA; break;
                    case 'c': currentColor = NamedTextColor.RED; break;
                    case 'd': currentColor = NamedTextColor.LIGHT_PURPLE; break;
                    case 'e': currentColor = NamedTextColor.YELLOW; break;
                    case 'f': currentColor = NamedTextColor.WHITE; break;
                    case 'k': obfuscated = true; break;
                    case 'l': bold = true; break;
                    case 'm': strikethrough = true; break;
                    case 'n': underline = true; break;
                    case 'o': italic = true; break;
                    case 'r':
                        currentColor = null;
                        bold = italic = underline = strikethrough = obfuscated = false;
                        break;
                }
            } else if (code.startsWith("#")) {
                currentColor = TextColor.fromHexString(code);
            }

            lastIndex = matcher.end();
        }

        // Add remaining text
        if (lastIndex < message.length()) {
            builder.append(Component.text(message.substring(lastIndex))
                    .color(currentColor)
                    .decoration(TextDecoration.BOLD, bold)
                    .decoration(TextDecoration.ITALIC, italic)
                    .decoration(TextDecoration.UNDERLINED, underline)
                    .decoration(TextDecoration.STRIKETHROUGH, strikethrough)
                    .decoration(TextDecoration.OBFUSCATED, obfuscated));
        }

        return builder.build();
    }
}
