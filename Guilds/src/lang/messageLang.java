package lang;

import files.langFile;

public class messageLang {
	
	public static String[] help = new String[]{
			"&7&m------------------------------------",
			"        &2&l&nGUILD&r",
			"",
			"&f/guild create <tên> &7- &aTạo guild",
			"&f/guild invite <tên> &7- &aMời vào guild",
			"&f/guild kick <tên> &7- &aĐuổi khỏi guild",
			"&f/guild addmanager <tên> &7- &aCho thành viên làm quản lý",
			"&f/guild removemanager <tên> &7- &aTước bỏ quyền quản lý của thành viên",
			"&f/guild setboard <...> &7- &aĐặt thông báo cho guild",
			"&f/guild setcustomname <tên> &7- &aĐặt tên custom(có màu) cho guild",
			"&f/guild disband &7- &aXóa guild",
			"&f/guild accept &7- &aChấp nhận xác nhận/lời mời",
			"&f/guild refuse &7- &aTừ chối xác nhận/lời mời",
			"",
			"&7&m------------------------------------",};
	
	public static String[] createGUIcreate = new String[]{
			"&7&m----------------------------------",
			"&fHãy nhập tên &bGUILD&f vào dòng chat",
			"&fChat '&e%word%&f' để hủy tạo guild",
			"&7&m----------------------------------",};
	public static String[] targetInviteMessage = new String[]{
			"&7&m----------------------------------",
			"&b%player%&f đã mời bạn vào guild &e%guild%",
			"&a/guild accept &f- &fChấp nhận",
			"&a/guild refuse &f- &fTừ chối",
			"&fLời mời hết hạn sau &e%s%&f giây!",
			"&7&m----------------------------------"};		
	public static String[] disbandmessage = new String[]{
			"&7&m----------------------------------",
			"&fXóa guild:",
			"&a/guild accept &f- &fChấp nhận",
			"&a/guild refuse &f- &fHủy bỏ",
			"&fYêu cầu xóa guild hết hạn sau &e%s%&f giây!",
			"&7&m----------------------------------"};	
	public static String[] stats = new String[]{
			"&7----------------------------------",			
			"   &9&lThông tin của guild",
			"&fTên guild: &b%guildName%",
			"&fThành lập vào lúc: &e%guildFoundedTime%",
			"&fThông báo: &f%board%",
			"&fTên custom: %customname%",
			"&fChủ sở hữu: &c%chuSoHuu%",
			"&fThành viên: &b%members%",
			"        &6&lCủa bạn",
			"&fRank: &a%rank%",
			"&fTham gia vào lúc: &e%joinDate%",};
			
	
	public static void register() {
        langFile.get().addDefault("help", help);
        langFile.get().addDefault("invaildCmd", "&cKhông tồn tại cú pháp này, sử dụng lệnh /guild help để biết thêm thông tin!");             
        langFile.get().addDefault("noRequest", "&cBạn chưa có lời mời nào để chấp nhận hoặc từ chối!");
        langFile.get().addDefault("noGuild", "&cBạn chưa có guild!");
        langFile.get().addDefault("papiNoGuild", "&fChưa có guild");        
        langFile.get().addDefault("guildDoesntExist", "&cGuild &b%guildName% &ckhông còn tồn tại!");      
        langFile.get().addDefault("playerNotFound", "&cNgười chơi không tồn tại!");
        langFile.get().addDefault("nameSake", "&cTên đối tượng không được giống tên của bạn!");
        langFile.get().addDefault("stats", stats);        
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
        langFile.get().addDefault("create.chat.cancel", "&fĐã hủy tạo guild!");
        langFile.get().addDefault("create.chat.maxLength", "&cQúa ký tự! Số ký tự giới hạn để đặt thông báo là &e%k%&c, số ký tự bạn đặt là &e%km%&c. Vui lòng nhập lại!");           
        langFile.get().addDefault("create.chat.denyCharacters.fullName", "&cTên guild không được là &c(&e%c%&c). Vui lòng nhập lại tên!");
        langFile.get().addDefault("create.chat.denyCharacters.Characters", "&cTên guild không được chứa ký tự &c(&e%c%&c). Vui lòng nhập lại tên!");        
        langFile.get().addDefault("invite.invitationSender.noper", "&cBạn cần là chủ guild hoặc quản lý guild để thực hiên điều này!");
        langFile.get().addDefault("invite.invitationSender.invite", "&bBạn đã gửi lời mời vào guild cho %player%");
        langFile.get().addDefault("invite.invitedRecipients.invite", targetInviteMessage);
        langFile.get().addDefault("invite.invitationSender.accept", "&e%player% &bđã chấp nhận lời mời!");
        langFile.get().addDefault("invite.invitedRecipients.accept", "&bBạn đã chấp nhận lời mời vào guild &e%guild%");
        langFile.get().addDefault("invite.invitedRecipients.refuse", "&fĐã từ chối lời mời!");
        langFile.get().addDefault("invite.invitationSender.expires", "&e%player% &btốn quá nhiều thời gian để chấp nhận lời mời, lời mời đã bị hủy!");
        langFile.get().addDefault("invite.invitationSender.refuse", "&e%player% &fđã từ chối lời mời!");
        langFile.get().addDefault("invite.invitationSender.self", "&cBạn không thể mời bản thân!");        
        langFile.get().addDefault("invite.invitationSender.requested", "&e%player% hiện đang có yêu cầu, vui lòng đợi!");
        langFile.get().addDefault("invite.invitationSender.alreadyInGuild", "&e%player%&c đã có guild!");
        langFile.get().addDefault("invite.invitationSender.targetQuit", "&e%player% đã thoát game, hủy lời mời!");         
        langFile.get().addDefault("invite.invitedRecipients.expires", "&bBạn tốn quá nhiều thời gian để chấp nhận lời mời!");
        langFile.get().addDefault("manager.noGuild.playerUseCmd", "&cBạn không có guild!");
        langFile.get().addDefault("manager.noGuild.target", "&e%player% &ckhông có guild!");
        langFile.get().addDefault("manager.targetIsNotInPGuild", "&e%player% &ckhông có trong guild của bạn!");     
        langFile.get().addDefault("manager.pIsNotTheOwner", "&cBạn không phải là chủ guild!");     
        langFile.get().addDefault("manager.targetAlreadyisCoOwner", "&e%player% &cđã là quản lý rồi!");
        langFile.get().addDefault("manager.targetAlreadyisCoOwner", "&e%player% &cchưa phải là quản lý!");
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
        langFile.get().addDefault("disband.accept", "&fXóa guild thành công!");
        langFile.get().addDefault("disband.refuse", "&fĐã hủy xóa guild!");
        langFile.get().addDefault("disband.error", "&cĐã xảy ra lỗi trong quá trình xóa guild!");
        langFile.get().addDefault("disband.expires", "&cĐã hết thời gian để xóa guild!");
        langFile.get().addDefault("disband.pIsNotTheO", "&cBạn cần là chủ guild!");
        langFile.get().addDefault("disband.PisNotinTheG", "&cBạn cần phải ở trong guild!");
        langFile.get().addDefault("setBoard.noGuild", "&cBạn cần phải ở trong guild!");
        langFile.get().addDefault("setBoard.noPer", "&cBạn cần phải là chủ guild hoặc quản lý guild!");
        langFile.get().addDefault("setBoard.maxLength", "&cQúa ký tự! Số ký tự giới hạn để đặt thông báo là &e%k%&c, số ký tự bạn đặt là &e%km%");        
        langFile.get().addDefault("setBoard.sucess", "&fBạn đã thay đổi thông báo guild thành: %board%");
        langFile.get().addDefault("setCustomName.noGuild", "&cBạn cần phải ở trong guild!");
        langFile.get().addDefault("setCustomName.noPer", "&cBạn cần phải là chủ guild hoặc quản lý guild!");
        langFile.get().addDefault("setCustomName.maxLength", "&cQúa ký tự! Số ký tự giới hạn để đặt tên custom là &e%k%&c, số ký tự bạn đặt là &e%km%");
        langFile.get().addDefault("setCustomName.sucess", "&fBạn đã thay đổi tên custom của guild thành: %name%");           
		langFile.get().options().copyDefaults(true);		
		langFile.save();	
			}

}
