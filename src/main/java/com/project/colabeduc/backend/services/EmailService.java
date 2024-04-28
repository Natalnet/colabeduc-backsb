package com.project.colabeduc.backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.colabeduc.backend.entities.Usuario;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordResetEmail(String toEmail, String token, Usuario user) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        try {
            helper.setTo(toEmail);
            helper.setSubject("Recuperação de Senha");
    
            String htmlContent = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
            "\n" +
            "<head>\n" +
            "  <!--[if gte mso 9]>\n" +
            "<xml>\n" +
            "  <o:OfficeDocumentSettings>\n" +
            "    <o:AllowPNG/>\n" +
            "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
            "  </o:OfficeDocumentSettings>\n" +
            "</xml>\n" +
            "<![endif]-->\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
            "  <!--[if !mso]><!-->\n" +
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
            "  <title></title>\n" +
            "  <style type=\"text/css\">\n" +
            "    @media only screen and (min-width: 620px) {\n" +
            "      .u-row {\n" +
            "        width: 600px !important;\n" +
            "      }\n" +
            "      .u-row .u-col {\n" +
            "        vertical-align: top;\n" +
            "      }\n" +
            "      .u-row .u-col-100 {\n" +
            "        width: 600px !important;\n" +
            "      }\n" +
            "    }\n" +
            "    @media (max-width: 620px) {\n" +
            "      .u-row-container {\n" +
            "        max-width: 100% !important;\n" +
            "        padding-left: 0px !important;\n" +
            "        padding-right: 0px !important;\n" +
            "      }\n" +
            "      .u-row .u-col {\n" +
            "        min-width: 320px !important;\n" +
            "        max-width: 100% !important;\n" +
            "        display: block !important;\n" +
            "      }\n" +
            "      .u-row {\n" +
            "        width: 100% !important;\n" +
            "      }\n" +
            "      .u-col {\n" +
            "        width: 100% !important;\n" +
            "      }\n" +
            "      .u-col>div {\n" +
            "        margin: 0 auto;\n" +
            "      }\n" +
            "    }\n" +
            "    body {\n" +
            "      margin: 0;\n" +
            "      padding: 0;\n" +
            "    }\n" +
            "    table,\n" +
            "    tr,\n" +
            "    td {\n" +
            "      vertical-align: top;\n" +
            "      border-collapse: collapse;\n" +
            "    }\n" +
            "    p {\n" +
            "      margin: 0;\n" +
            "    }\n" +
            "    .ie-container table,\n" +
            "    .mso-container table {\n" +
            "      table-layout: fixed;\n" +
            "    }\n" +
            "    * {\n" +
            "      line-height: inherit;\n" +
            "    }\n" +
            "    a[x-apple-data-detectors='true'] {\n" +
            "      color: inherit !important;\n" +
            "      text-decoration: none !important;\n" +
            "    }\n" +
            "    table,\n" +
            "    td {\n" +
            "      color: #000000;\n" +
            "    }\n" +
            "    #u_body a {\n" +
            "      color: #0000ee;\n" +
            "      text-decoration: underline;\n" +
            "    }\n" +
            "  </style>\n" +
            "  <!--[if !mso]><!-->\n" +
            "  <link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "  <!--<![endif]-->\n" +
            "</head>\n" +
            "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #f9f9f9;color: #000000\">\n" +
            "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
            "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
            "  <table id=\"u_body\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #f9f9f9;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "    <tbody>\n" +
            "      <tr style=\"vertical-align: top\">\n" +
            "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
            "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
            "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
            "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #003399;\">\n" +
            "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
            "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #003399;\"><![endif]-->\n" +
            "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
            "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
            "                  <div style=\"height: 100%;width: 100% !important;\">\n" +
            "                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
            "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
            "                        <tbody>\n" +
            "                          <tr>\n" +
            "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
            "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                  <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
            "                                    <img align=\"center\" border=\"0\" src=\"https://assets.unlayer.com/projects/230012/1714254351533-Frame%201email.jpg\" alt=\"\" title=\"\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 580px;\" width=\"580\" />\n" +
            "                                  </td>\n" +
            "                                </tr>\n" +
            "                              </table>\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                        </tbody>\n" +
            "                      </table>\n" +
            "                    </div><!--<![endif]-->\n" +
            "                  </div>\n" +
            "                </div>\n" +
            "                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
            "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
            "              </div>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
            "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
            "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
            "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
            "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
            "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
            "                  <div style=\"height: 100%;width: 100% !important;\">\n" +
            "                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
            "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
            "                        <tbody>\n" +
            "                          <tr>\n" +
            "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:33px 55px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
            "                              <div style=\"font-size: 14px; line-height: 160%; text-align: center; word-wrap: break-word;\">\n" +
            "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 18px; line-height: 28.8px; color: #000;\">Olá,&nbsp; [[username]], </span></p>\n" +
            "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 18px; line-height: 28.8px; color: #000;\">Uma redifinição de senha foi solicitada para a sua conta.</span></p>\n" +
            "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 18px; line-height: 28.8px; color: #000;\">Clique no botão abaixo para alterar sua senha.</span></p>\n" +
            "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 18px; line-height: 28.8px; color: #000;\">Observe que o link é válido por 24 horas. </span></p>\n" +
            "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 18px; line-height: 28.8px; color: #000;\">Depois que o limite de tempo expirar, você precisará reenviar a solicitação de redefinição de&nbsp;senha.</span></p>\n" +
            "                                <p style=\"font-size: 14px; line-height: 160%;\">&nbsp;</p>\n" +
            "                              </div>\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                        </tbody>\n" +
            "                      </table>\n" +
            "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
            "                        <tbody>\n" +
            "                          <tr>\n" +
            "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
            "                              <div align=\"center\">\n" +
            "                                <a href=\"http://localhost:8080/api/usuarios/resetar-senha?token=" + token + "\" + target=\"_blank\" class=\"v-button\" style=\"box-sizing: border-box;display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #FFFFFF; background-color: #ff6600; border-radius: 4px;-webkit-border-radius: 4px; -moz-border-radius: 4px; width:auto; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;font-size: 14px;\">\n" +
            "                                  <span style=\"display:block;padding:14px 44px 13px;line-height:120%;\"><span style=\"font-size: 16px; line-height: 19.2px;\"><strong><span style=\"line-height: 19.2px; font-size: 16px;\">ALTERAR SUA SENHA</span></strong></span></span>\n" +
            "                                </a>\n" +
            "                              </div>\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                        </tbody>\n" +
            "                      </table>\n" +
            "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
            "                        <tbody>\n" +
            "                          <tr>\n" +
            "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:33px 55px 60px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
            "                              <div style=\"font-size: 14px; line-height: 160%; text-align: center; word-wrap: break-word;\">\n" +
            "                                <p style=\"line-height: 160%; font-size: 14px;\"><span style=\"font-size: 18px; line-height: 28.8px;\">Atenciosamente,</span></p>\n" +
            "                                <p style=\"line-height: 160%; font-size: 14px;\"><span style=\"font-size: 18px; line-height: 28.8px;\">Equipe ColabEduc</span></p>\n" +
            "                              </div>\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                        </tbody>\n" +
            "                      </table>\n" +
            "                    </div><!--<![endif]-->\n" +
            "                  </div>\n" +
            "                </div>\n" +
            "                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
            "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
            "              </div>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
            "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #003399;\">\n" +
            "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
            "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #003399;\"><![endif]-->\n" +
            "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"background-color: #ffffff;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
            "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
            "                  <div style=\"width: 100% !important;\">\n" +
            "                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                    <div style=\"box-sizing: border-box; width: 100% !important;\">\n" +
            "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
            "                        <tbody>\n" +
            "                          <tr>\n" +
            "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:24px 10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
            "                              <div style=\"color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word;\">\n" +
            "                                <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 16px; line-height: 16.8px;\">ColabEduc © Todos os direitos reservados</span></p>\n" +
            "                              </div>\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                        </tbody>\n" +
            "                      </table>\n" +
            "                    </div><!--<![endif]-->\n" +
            "                  </div>\n" +
            "                </div>\n" +
            "                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
            "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
            "              </div>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "        </td>\n" +
            "      </tr>\n" +
            "    </tbody>\n" +
            "  </table>\n" +
            "  <!--[if (mso)|(IE)]></div><![endif]-->\n" +
            "  <!--[if (mso)|(IE)]></div><![endif]-->\n" +
            "</body>\n" +
            "\n" +
            "</html>";

            htmlContent = htmlContent.replace("[[username]]", user.getUsername());

            helper.setText(htmlContent, true);
    
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}

