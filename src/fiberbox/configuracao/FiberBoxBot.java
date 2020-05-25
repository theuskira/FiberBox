package fiberbox.configuracao;

import fiberbox.model.Caixa;
import fiberbox.model.Estatico;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author Matheus - DELL
 */
public class FiberBoxBot extends TelegramLongPollingBot{

    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        
        if (update.hasMessage() && update.getMessage().hasText()) {
            
            String command = update.getMessage().getText();
            
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            
            message.setParseMode("HTML");
            
            switch(command){
                case "/start":
                    
                    message.setText("<b>COMANDOS DISPONÍVEIS</b>\n" +
                                "/status - Status do Sistema\n" +
                                "/caixas_on - Número de Caixas On\n" +
                                "/caixas_off - Número de Caixas Off\n" +
                                "/total_usuarios - Total de Usuários\n" +
                                "/listar_caixas_off - Listar Caixas Off\n" +
                                "/listar_caixas_on - Listar Caixas On");
                    break;
                    
                case "/status":
                    
                    message.setText("<b>Status do Sistema</b>\n"
                            + "Total de Usuários: <b>" + Estatico.getTotalUsuarios() + "</b>\n"
                            + "Caixas On-Line: <b>" + Estatico.getCaixasOn()+ "</b>\n"
                            + "Caixas Off-Line: <b>" + Estatico.getCaixasOff()+ "</b>\n");
                    
                    break;
                    
                case "/caixas_on":
                    
                    message.setText(Estatico.getCaixasOn() + " caixa(s) On-Line");
                    break;

                case "/caixas_off":
                    
                    message.setText(Estatico.getCaixasOff() + " caixa(s) Off-Line");
                    break;
                    
                case "/total_usuarios":
                    
                    message.setText(Estatico.getTotalUsuarios()+ " usuário(s) On-Line");
                    break;
                    
                case "/listar_caixas_off":
                    
                    if(Estatico.getCaixasOff() > 0){
                        
                        String listaCaixasOff = "<b>Caixa(s) Off-Line: " + Estatico.getCaixasOff() + "</b>";
                    
                        for(Caixa c: Estatico.getListaCaixasOff()){

                            String usuarios = "";

                            if(c.getUsuario1() != null && !c.getUsuario1().isEmpty()){
                                usuarios += c.getUsuario1() + "\n";
                            }
                            if(c.getUsuario2() != null && !c.getUsuario2().isEmpty()){
                                usuarios += c.getUsuario2() + "\n";
                            }
                            if(c.getUsuario3() != null && !c.getUsuario3().isEmpty()){
                                usuarios += c.getUsuario3() + "\n";
                            }
                            if(c.getUsuario4() != null && !c.getUsuario4().isEmpty()){
                                usuarios += c.getUsuario4() + "\n";
                            }
                            if(c.getUsuario5() != null && !c.getUsuario5().isEmpty()){
                                usuarios += c.getUsuario5() + "\n";
                            }
                            if(c.getUsuario6() != null && !c.getUsuario6().isEmpty()){
                                usuarios += c.getUsuario6() + "\n";
                            }
                            if(c.getUsuario7() != null && !c.getUsuario7().isEmpty()){
                                usuarios += c.getUsuario7() + "\n";
                            }
                            if(c.getUsuario8() != null && !c.getUsuario8().isEmpty()){
                                usuarios += c.getUsuario8() + "\n";
                            }

                            listaCaixasOff += "\n\nCódigo: <b>" + c.getCodigo() + "</b>" +
                                    "\n" + usuarios + "Endereço: " + c.getEndereco();

                            message.setText(listaCaixasOff);

                        }
                        
                    }else{
                        
                        message.setText(Estatico.getCaixasOff() + " caixa(s) Off-Line");
                        
                    }
                    
                break;    
                
                case "/listar_caixas_on":
                    
                    String listaCaixasOn = "<b>Caixa(s) On-Line: " + Estatico.getCaixasOn() +"</b>";
                    
                    for(Caixa c: Estatico.getListaCaixasOn()){
                        
                        String usuarios = "";
                        
                        if(c.getUsuario1() != null && !c.getUsuario1().isEmpty()){
                            usuarios += c.getUsuario1() + "\n";
                        }
                        if(c.getUsuario2() != null && !c.getUsuario2().isEmpty()){
                            usuarios += c.getUsuario2() + "\n";
                        }
                        if(c.getUsuario3() != null && !c.getUsuario3().isEmpty()){
                            usuarios += c.getUsuario3() + "\n";
                        }
                        if(c.getUsuario4() != null && !c.getUsuario4().isEmpty()){
                            usuarios += c.getUsuario4() + "\n";
                        }
                        if(c.getUsuario5() != null && !c.getUsuario5().isEmpty()){
                            usuarios += c.getUsuario5() + "\n";
                        }
                        if(c.getUsuario6() != null && !c.getUsuario6().isEmpty()){
                            usuarios += c.getUsuario6() + "\n";
                        }
                        if(c.getUsuario7() != null && !c.getUsuario7().isEmpty()){
                            usuarios += c.getUsuario7() + "\n";
                        }
                        if(c.getUsuario8() != null && !c.getUsuario8().isEmpty()){
                            usuarios += c.getUsuario8() + "\n";
                        }
                        
                        listaCaixasOn += "\n\nCódigo: <b>" + c.getCodigo() + "</b>" +
                                "\n" + usuarios + "Endereço: " + c.getEndereco();
                        
                    }
                    
                    message.setText(listaCaixasOn);
                    
                    break;
                    
            }
            
            message.setChatId(update.getMessage().getChatId());
            
            System.out.println("CHAT ID: " + update.getMessage().getChatId());
            System.out.println("Mensagem: " + command);
            
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "fiberbox@dwtelecom_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "1056315587:AAF3idFA3K_-HmwmsDC1FxsbSpBwp-zP42E";
    }
    
}
