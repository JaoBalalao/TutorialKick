package net.targon.atlvntis.tutorialkick;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {

    public void onEnable() {

        getCommand("kick").setExecutor(this);

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("kick")) { //Checamos se o comando enviado foi /kick.

            if(!(sender instanceof Player)) //Checamos se quem enviou é um Jogador. Caso não seja, retorna.
                return true;

            Player player = (Player) sender; //Definimos a variável do player.

            if(!player.hasPermission("tutorialkick.kick")) { //Checamos se o player tem a permissão. Caso não tenha...
                player.sendMessage("§cSem permissão"); //Mandamos uma mensagem...
                return true; // e retornamos.
            }

            //o comando será /kick <jogador> <motivo>

            if(args.length < 2) { //Checamos se o comando tem os argumentos necessários. Caso não tenha...
                player.sendMessage("§cUse /kick <jogador> <motivo>"); //Mandamos uma mensagem...
                return true; // e retornamos.
            }

            Player target = Bukkit.getPlayerExact(args[0]); //Definimos a variável do alvo.

            if(target == null || !target.isOnline()) { //Se o alvo não estiver online ou for nulo...
                player.sendMessage("§cJogador não encontrado."); //Mandamos uma mensagem...
                return true; // e retornamos.
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 1; i < args.length; i++) { //Loopamos o motivo do kick.
                sb.append(args[i]);
                sb.append(" ");
            }

            Bukkit.broadcastMessage("§eO jogador §f" + target.getName() + " §efoi expulso por §f" + player.getName() + "§e.");
            Bukkit.broadcastMessage("§eMotivo: §f" + sb.toString()); //Mandamos mensagem ao servidor inteiro.

            target.kickPlayer("§eVocê foi kickado por §f" + player.getName() + "§e!\n§eMotivo: §f" + sb.toString()); //Kickamos o jogador.

            return true; //Retornamos.

        }

        return false;

    }
}
