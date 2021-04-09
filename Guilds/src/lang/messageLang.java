package lang;

import files.langFile;

public class messageLang {
	
	public static String[] help = new String[]{
			"&7-------------------------------",
			"&2&l&nGUILD&r &7- &e%version%",
			"",
			"&f/guild create &e<...>",
			"&f/guild disband",
			"&f/guild setcustomname &e<...>",
			"&f/guild setboard &e<...>",
			"&f/guild invite &e<...>",
			"&f/guild kick &e<...>",
			"&f/guild addmanager &e<...>",
			"&f/guild removemanager &e<...>",
			"&f/guild stats",
			"&f/guild accept",
			"&f/guild deny",	
			"&7-------------------------------"			
			};
	
	public static String[] createGUIcreate = new String[]{
			"&7&m----------------------------------",
			"&fHãy nhập tên &bGUILD&f vào dòng chat",
			"&fChat '&e%word%&f' để hủy tạo guild",
			"&7&m----------------------------------"
			};
	public static String[] targetInviteMessage = new String[]{
			"&7-------------------------------",			
			"  &f&lLỜI MỜI VÀO GUILD &b%guild%",
			"   %customname%",
			"    &fBởi: &2%player%",		
			"    &fThời gian: &e%s% giây"	
			};
	public static String[] disbandmessage = new String[]{
			"&7-------------------------------",			
			"       &4&lXÓA GUILD",
			"    &fThời gian: &e%s% giây"
			};
	public static String[] stats = new String[]{
			"&7----------------------------------",			
			" &4&lTHÔNG TIN GUILD &8>",
			"&fTên: &b%guildName%",
			"&fTên custom: %customname%",
			"&fThông báo: %board%",
			"&fThời gian thành lập: &e%guildFoundedTime%",
			"&fChủ guild: &e%chuSoHuu%",
			"&fSố thành viên: &e%members%",
			" &4&lTHÔNG TIN CỦA BẠN &8>",
			"&fRank: &E%rank%",
			"&fThời gian tham gia: &e%joinDate%",
			"&7----------------------------------"
			};
			
	
	public static void register() {
        langFile.get().addDefault("help", help);
        langFile.get().addDefault("invaildCmd", "&cKhông tồn tại cú pháp này, sử dụng lệnh /guild help để biết thêm thông tin!");             
        langFile.get().addDefault("noGuild", "&cBạn chưa có guild!");
        langFile.get().addDefault("papiNoGuild", "&fChưa có guild");        
        langFile.get().addDefault("guildDoesntExist", "&cGuild &b%guildName% &ckhông còn tồn tại!");      
        langFile.get().addDefault("playerNotFound", "&cNgười chơi không tồn tại!");
        langFile.get().addDefault("nameSake", "&cTên đối tượng không được giống tên của bạn!");
        langFile.get().addDefault("waiting", "&fVui lòng đợi...");
        langFile.get().addDefault("submitSuccessfullyJoin", "&aĐã gửi đơn xin gia nhập thành công!");        
        langFile.get().addDefault("stats", stats);
        langFile.get().addDefault("noRequest.accept", "&cBạn chưa có lời mời/đề nghị nào để chấp nhận!");
        langFile.get().addDefault("noRequest.deny", "&cBạn chưa có lời mời/đề nghị nào để từ chối!");
        langFile.get().addDefault("status.offline", "&4Không trực tuyến");
        langFile.get().addDefault("status.online", "&2Trực tuyến");
        langFile.get().addDefault("timeLeft.invite", "&cBạn cần đợi &e%second% giây&c nữa để có thể mời tiếp!");
        langFile.get().addDefault("timeLeft.disband", "&cBạn cần đợi &e%second% giây&c nữa để có thể yêu cầu xóa guild tiếp!");   
        langFile.get().addDefault("create.createGuild", "&bBạn vừa tạo guild mới với tên là: &e&l%name%");
        langFile.get().addDefault("create.alreadyInGuild", "&cBạn không thể tạo guild vì đang ở trong guild &e%guild%");
        langFile.get().addDefault("create.guildAlreadyExist", "&cTên guild bạn nhập đã tồn tại!");
        langFile.get().addDefault("create.haveRequest", "&cBạn không thể tạo guild khi đang có lời mời/yêu cầu");
        langFile.get().addDefault("create.maxLength", "&cQúa ký tự! Số ký tự giới hạn để đặt thông báo là &e%k%&c, số ký tự bạn đặt là &e%km%");
        langFile.get().addDefault("create.request.money", "&cBạn chưa đủ tiền! Số tiền hiện tại của bạn là: &e%p%&c. Số tiền cần để tạo guild là: &e$%pq%");
        langFile.get().addDefault("create.request.point", "&cBạn chưa đủ points! Số points hiện tại của bạn là: &e%p%&c. Số points cần để tạo guild là: &e%pq%");
        langFile.get().addDefault("create.request.token", "&cBạn chưa đủ tokens! Số tokens hiện tại của bạn là: &e%p%&c. Số tokens cần để tạo guild là: &e%pq%");
        langFile.get().addDefault("create.GUI.create", createGUIcreate);          
        langFile.get().addDefault("create.GUI.inProgess", "&cBạn đang trong tiến trình tạo guild! Vui lòng nhập tên guild dưới dòng chat!");  
        langFile.get().addDefault("create.denyCharacters.fullName", "&cTên guild không được là &c(&e%c%&c)");
        langFile.get().addDefault("create.denyCharacters.Characters", "&cTên guild không được chứa ký tự &c(&e%c%&c)");
        langFile.get().addDefault("create.chat.cancel", "&4Đã hủy tạo guild!");    
        langFile.get().addDefault("invite.invitationSender.noper", "&cBạn cần là chủ guild hoặc quản lý guild để thực hiên điều này!");
        langFile.get().addDefault("invite.invitationSender.invite", "&fBạn đã gửi lời mời vào guild cho &e%player%");
        langFile.get().addDefault("invite.invitedRecipients.invite", targetInviteMessage);
        langFile.get().addDefault("invite.invitationSender.accept", "&e%player% &fđã chấp nhận lời mời!");
        langFile.get().addDefault("invite.invitedRecipients.accept", "&fBạn đã chấp nhận lời mời vào guild &e%guild%");
        langFile.get().addDefault("invite.invitedRecipients.deny", "&4Đã từ chối lời mời!");
        langFile.get().addDefault("invite.invitationSender.expires", "&e%player% &ftốn quá nhiều thời gian để chấp nhận lời mời, lời mời đã bị hủy!");
        langFile.get().addDefault("invite.invitationSender.deny", "&e%player% &fđã từ chối lời mời!");     
        langFile.get().addDefault("invite.invitationSender.requested", "&e%player% hiện đang có yêu cầu, vui lòng đợi!");
        langFile.get().addDefault("invite.invitationSender.alreadyInGuild", "&e%player%&c đã có guild!");       
        langFile.get().addDefault("invite.invitedRecipients.expires", "&4Bạn tốn quá nhiều thời gian để chấp nhận lời mời! Lời mời đã bị hủy");
        langFile.get().addDefault("manager.noGuild.target", "&e%player% &ckhông có guild!");
        langFile.get().addDefault("manager.targetIsNotInPGuild", "&e%player% &ckhông có trong guild của bạn!");     
        langFile.get().addDefault("manager.pIsNotTheOwner", "&cBạn không phải là chủ guild!");     
        langFile.get().addDefault("manager.targetAlreadyisManager", "&e%player% &cđã là quản lý rồi!");
        langFile.get().addDefault("manager.targetIsNotManger", "&e%player% &cchưa phải là quản lý!");
        langFile.get().addDefault("manager.add.sucess.P", "&aBạn đã cho &e%player% &alên chức quản lý guild!");   
        langFile.get().addDefault("manager.add.sucess.T", "&aBạn đã được lên chức quản lý guild bởi &e%player%");
        langFile.get().addDefault("manager.remove.sucess.P", "&aBạn đã tước quyền quản lý của &e%player%");   
        langFile.get().addDefault("manager.remove.sucess.T", "&cBạn đã bị tước quyền quản lý bởi &e%player%");  
        langFile.get().addDefault("kick.pIsNotHaveThePower", "&cBạn cần là chủ guild hoặc quản lý guild");
        langFile.get().addDefault("kick.pIsCOandTisO", "&cBạn không thể đuổi chủ guild ra khỏi guild!");
        langFile.get().addDefault("kick.targetIsNotInPGuild", "&e%player% &ckhông có trong guild của bạn!");
        langFile.get().addDefault("kick.noGuild.playerUseCmd", "&cBạn không có guild!");
        langFile.get().addDefault("kick.noGuild.target", "&e%player% &ckhông có guild!");
        langFile.get().addDefault("kick.sucess.P", "&fBạn đã đuổi &a%player%&f ra khỏi guild!");
        langFile.get().addDefault("kick.sucess.T", "&fBạn đã bị đuổi khỏi guild bởi &a%player% &f(&b%rank%&f)");
        langFile.get().addDefault("disband.message", disbandmessage);
        langFile.get().addDefault("disband.haveRequest", "&cBạn không thể xóa guild khi đang có yêu cầu/lời mời");
        langFile.get().addDefault("disband.accept", "&aXóa guild thành công!");
        langFile.get().addDefault("disband.deny", "&4Đã hủy xóa guild!");
        langFile.get().addDefault("disband.error", "&cĐã xảy ra lỗi trong quá trình xóa guild!");
        langFile.get().addDefault("disband.expires", "&cĐã hết thời gian để xóa guild!");
        langFile.get().addDefault("disband.pIsNotTheO", "&cBạn cần là chủ guild!");
        langFile.get().addDefault("disband.PisNotinTheG", "&cBạn cần phải ở trong guild!");
        langFile.get().addDefault("setBoard.noPer", "&cBạn cần phải là chủ guild hoặc quản lý guild!");
        langFile.get().addDefault("setBoard.maxLength", "&cQúa ký tự! Số ký tự giới hạn để đặt thông báo là &e%k%&c, số ký tự bạn đặt là &e%km%");        
        langFile.get().addDefault("setBoard.sucess", "&fBạn đã thay đổi thông báo guild thành: %board%");
        langFile.get().addDefault("setCustomName.noPer", "&cBạn cần phải là chủ guild hoặc quản lý guild!");
        langFile.get().addDefault("setCustomName.maxLength", "&cQúa ký tự! Số ký tự giới hạn để đặt tên custom là &e%k%&c, số ký tự bạn đặt là &e%km%");
        langFile.get().addDefault("setCustomName.sucess", "&fBạn đã thay đổi tên custom của guild thành: %name%");           
		langFile.get().options().copyDefaults(true);		
		langFile.save();	
			}

}
