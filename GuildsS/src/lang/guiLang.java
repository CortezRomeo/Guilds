package lang;

import files.guiFile;

public class guiLang {
	
	public static String[] createlore = new String[]{
			"",
			"&fKhông muốn gia nhập guild khác?",
			"&fTạo ngay guild mới!",
			"",
			"&eNhấn vào đây!"
			};
	public static String[] listGuild = new String[]{
			"",
			"&fTổng guild hiện có: &a%totalguild%",
			"",
			"&fXem danh sách các guild đang",
			"&ftồn tại để có thể xin gia nhập.",
			"",
			"&eNhấn vào đây!"
			};	
	public static String[] prevPage = new String[]{
			"",
			"&fTrang hiện tại: &b%trang%",
			"&fNhấn vào đây để về trang &a%trangtruoc%",
			""
			};		
	public static String[] pageInfo = new String[]{
			"",
			"&fBạn đang ở trang: &a%trang% &f/ &a%trangcuoi%",		
			""
			};	
	public static String[] nextPage = new String[]{
			"",
			"&fTrang hiện tại: &b%trang%",
			"&fNhấn vào đây để sang trang &a%trangsau%",
			""
			};		
	public static String[] guildLore = new String[]{
			"",
			"&fTên custom: %customname%",
			"&fThông báo: %board%",
			"&fTG Thành lập: &e%foundedTime%",			
			"",
			"&fChủ guild: &a%chuSoHuu%",
			"&fThành viên: &b%totalMembers%",
			"",
			"&eNhấn để thao tác!"
			};		
	public static String[] gInfo = new String[]{
			"",
			"&fTên custom: %customname%",
			"&fThông báo: %board%",
			"",
			"&fThành viên: &b%totalMembers%",
			"&fChủ guild: &a%chuSoHuu%",
			"",
			"&fTG thành lập: &e%foundedTime%",
			""
			};		
	public static String[] gMembers = new String[]{
			"",
			"&fTổng thành viên: &a%totalMembers%",
			"",
			"&eNhấn vào để xem chi tiết"
			};		
	public static String[] memberStats = new String[]{
			"",
			"&fRank: &a%rank%",
			"&fTG gia nhập: &e%joinDate%",
			"&fTrạng thái: %status%",
			""
			};
	public static String[] requestJoin = new String[]{
			"",
			"&fGửi yêu cầu gia nhập,",
			"&fKhi gửi sẽ không thể hủy yêu cầu",
			"",
			"&eNhấn để gửi"
			};		
	public static String[] requestJoinNonNull = new String[]{
			"",
			"&fBạn không thể gửi yêu cầu gia nhập",
			"&fvì bạn đã gửi yêu cầu gia nhập guild",
			"&fnày trước đó",			
			""
			};		
	public static String[] back = new String[]{
			"&eNhấn để trở về trang trước"				
			};	
	public static String[] disbandtime = new String[]{
			"&eVui lòng đợi %seconds% giây nữa để có thể",
			"&ebấm xóa"
			};	
	public static String[] disband = new String[]{
			"&eNhấn vào để xóa"
			};
	public static String[] cancel = new String[]{
			"&eNhấn vào để hủy"
			};		
			
	
	
	public static void register() {
		
		guiFile.get().options().header(
				"ĐỌC TRƯỚC KHI CONFIG FILE! \n"+
				"\n"+
				"Có tổng cộng 6 rows bạn có thể set cho GUI \n"+
				"rows 1 : 9 Ô \n"+
				"rows 2 : 18 Ô \n"+
				"rows 3 : 27 Ô \n"+
				"rows 4 : 36 Ô \n"+
				"rows 5 : 45 Ô \n"+
				"rows 6 : 54 Ô \n"+
				"Đừng đặt rows>6 để tránh lỗi GUI \n"+
				"\n"+
				"Có 2 loại type item bạn có thể chọn là: \n"+
				"+ material \n"+
				"+ customhead \n"+
				"\n"+
				"Nếu đặt type là material thì value bạn chọn là tên của item \n"+
				"Ví dụ: GRASS ; STONE ; BOOK \n"+
				"ID item cũng là thứ bạn cần quan tâm khi chọn value là material \n"+
				"Nếu thấy item trong GUI bị lỗi (Màu tím và đen) thì bạn cần chỉnh \n"+
				"lại id lại cho hợp lý (0 hoặc 1) \n"+
				"\n"+
				"Nếu đặt type là customhead thì value bạn chọn có thể lựa chọn tại URL: \n"+
				"https://minecraft-heads.com/custom-heads \n"+
				"Chọn vào head bạn thích rồi kéo xuống cùng phần Other: và copy hết phần \n"+
				"''Value:'' vào phần value cho item bạn đang config (Chú ý dấu các dấu bằng) \n"+
				"Phần ID item bạn không cần quan tâm khi đặt type là customhead \n"+
				"\n"+
				"Chú ý về slot item khi đặt rows gui để tránh bị lỗi"				
				
				);
		
		
		guiFile.get().addDefault("borderItem.type", "material");
		guiFile.get().addDefault("borderItem.value", "STAINED_GLASS_PANE");
		guiFile.get().addDefault("borderItem.id", 15);
		guiFile.get().addDefault("borderItem.name", "");
		guiFile.get().addDefault("borderItem.lore", "");
		
		guiFile.get().addDefault("nextPage.type", "customhead");		
		guiFile.get().addDefault("nextPage.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19");
		guiFile.get().addDefault("nextPage.id",1);
		guiFile.get().addDefault("nextPage.name", "&3&lTrang &a%trang%&f/&c%trangcuoi%");
		guiFile.get().addDefault("nextPage.lore", nextPage);
		
		guiFile.get().addDefault("pageInfo.type", "material");	
		guiFile.get().addDefault("pageInfo.value", "BOOK");
		guiFile.get().addDefault("pageInfo.id",0);
		guiFile.get().addDefault("pageInfo.name", "&bTôi đang ở đâu?");
		guiFile.get().addDefault("pageInfo.lore", pageInfo);	
		
		guiFile.get().addDefault("prevPage.type", "customhead");		
		guiFile.get().addDefault("prevPage.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==");
		guiFile.get().addDefault("prevPage.id",1);
		guiFile.get().addDefault("prevPage.name", "&3&lTrang &a%trang%&f/&c%trangcuoi%");
		guiFile.get().addDefault("prevPage.lore", prevPage);
		
		guiFile.get().addDefault("back.type", "customhead");		
		guiFile.get().addDefault("back.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ");
		guiFile.get().addDefault("back.id",1);
		guiFile.get().addDefault("back.name", "&cTrở về trang trước");
		guiFile.get().addDefault("back.lore", back);
		
		// ---------------
		
		guiFile.get().addDefault("gui.noGuild.enableBorder", true);
		guiFile.get().addDefault("gui.noGuild.rows", 3);
		guiFile.get().addDefault("gui.noGuild.title", "&8Guild");
		
		guiFile.get().addDefault("gui.noGuild.items.create.type", "customhead");		
		guiFile.get().addDefault("gui.noGuild.items.create.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA1NmJjMTI0NGZjZmY5OTM0NGYxMmFiYTQyYWMyM2ZlZTZlZjZlMzM1MWQyN2QyNzNjMTU3MjUzMWYifX19");
		guiFile.get().addDefault("gui.noGuild.items.create.id",1);
		guiFile.get().addDefault("gui.noGuild.items.create.name", "&aTạo guild mới");
		guiFile.get().addDefault("gui.noGuild.items.create.slot", 11);	
		guiFile.get().addDefault("gui.noGuild.items.create.lore", createlore);
				
		guiFile.get().addDefault("gui.noGuild.items.listGuild.type", "customhead");		
		guiFile.get().addDefault("gui.noGuild.items.listGuild.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGE2MWFjMWE0ODA2ZGZjNGVhYmQ3N2NjOWM0Y2QwYjllYTAzZDA4MWJmOTM1MTllM2ZmMTQ4NDMzZmEyMDRjZSJ9fX0=");
		guiFile.get().addDefault("gui.noGuild.items.listGuild.id",1);
		guiFile.get().addDefault("gui.noGuild.items.listGuild.name", "&3Danh sách guild");
		guiFile.get().addDefault("gui.noGuild.items.listGuild.slot", 15);	
		guiFile.get().addDefault("gui.noGuild.items.listGuild.lore", listGuild);
		
		guiFile.get().addDefault("gui.noGuild.items.close.type", "material");
		guiFile.get().addDefault("gui.noGuild.items.close.value", "BARRIER");
		guiFile.get().addDefault("gui.noGuild.items.close.id", 0);
		guiFile.get().addDefault("gui.noGuild.items.close.name", "&cĐóng");
		guiFile.get().addDefault("gui.noGuild.items.close.slot", 22);		
		guiFile.get().addDefault("gui.noGuild.items.close.lore", "");
		
		// -------------		
		
		guiFile.get().addDefault("gui.disband.enableBorder", true);
		guiFile.get().addDefault("gui.disband.rows", 3);
		guiFile.get().addDefault("gui.disband.title", "&8Xóa guild");
		
		guiFile.get().addDefault("gui.disband.items.seconds!=0.type", "material");		
		guiFile.get().addDefault("gui.disband.items.seconds!=0.value",
		"BARRIER");
		guiFile.get().addDefault("gui.disband.items.seconds!=0.id",1);
		guiFile.get().addDefault("gui.disband.items.seconds!=0.name", "&cHãy suy nghĩ thật kĩ!");
		guiFile.get().addDefault("gui.disband.items.seconds!=0.slot", 11);	
		guiFile.get().addDefault("gui.disband.items.seconds!=0.lore", disbandtime);
		
		guiFile.get().addDefault("gui.disband.items.seconds==0.type", "material");		
		guiFile.get().addDefault("gui.disband.items.seconds==0.value",
		"WOOL");
		guiFile.get().addDefault("gui.disband.items.seconds==0.id",14);
		guiFile.get().addDefault("gui.disband.items.seconds==0.name", "&4&lXÓA");
		guiFile.get().addDefault("gui.disband.items.seconds==0.slot", 11);	
		guiFile.get().addDefault("gui.disband.items.seconds==0.lore", disband);
		
		guiFile.get().addDefault("gui.disband.items.cancel.type", "customhead");		
		guiFile.get().addDefault("gui.disband.items.cancel.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ==");
		guiFile.get().addDefault("gui.disband.items.cancel.id",14);
		guiFile.get().addDefault("gui.disband.items.cancel.name", "&cHủy");
		guiFile.get().addDefault("gui.disband.items.cancel.slot", 15);	
		guiFile.get().addDefault("gui.disband.items.cancel.lore", cancel);				
		
		// -------------
		
		
		guiFile.get().addDefault("gui.listGuild.rows", 6);
		guiFile.get().addDefault("gui.listGuild.title", "&8Danh sách guild");
		
		guiFile.get().addDefault("gui.listGuild.items.guild.type", "material");		
		guiFile.get().addDefault("gui.listGuild.items.guild.value",
		"KNOWLEDGE_BOOK");
		guiFile.get().addDefault("gui.listGuild.items.guild.id",0);
		guiFile.get().addDefault("gui.listGuild.items.guild.name", "&9%guildName%");
		guiFile.get().addDefault("gui.listGuild.items.guild.lore", guildLore);
		
		guiFile.get().addDefault("gui.listGuild.items.nextPage.slot", 52);
		guiFile.get().addDefault("gui.listGuild.items.pageInfo.slot", 49);
		guiFile.get().addDefault("gui.listGuild.items.back.slot", 50);	
		guiFile.get().addDefault("gui.listGuild.items.prevPage.slot", 46);			
		
		// -------------
		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.enableBorder", true);
		guiFile.get().addDefault("gui.GuestViewGuildInfo.rows", 3);
		guiFile.get().addDefault("gui.GuestViewGuildInfo.title", "&8Thông tin guild &8(&9%guildName%&8)");
		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.info.type", "material");		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.info.value",
		"KNOWLEDGE_BOOK");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.info.id",0);
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.info.name", "&9%guildName%");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.info.slot", 4);	
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.info.lore", gInfo);
		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.members.type", "customhead");		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.members.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTNhNDdiMTMxZGJjYmMzMGYzY2JlOTY0NzdmYTVlMzcyMjY4MmZhYjk5NWNkMmQyMmY3NWIxNWU5NDgyZmQyMyJ9fX0=");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.members.id",1);
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.members.name", "&bThành viên");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.members.slot", 11);	
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.members.lore", gMembers);
		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNull.type", "customhead");		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNull.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzMzlmZjJlNTM0MmJhMThiZGM0OGE5OWNjYTY1ZDEyM2NlNzgxZDg3ODI3MmY5ZDk2NGVhZDNiOGFkMzcwIn19fQ==");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNull.id",1);
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNull.name", "&aXin vào guild");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNull.slot", 15);	
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNull.lore", requestJoin);
		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNonNull.type", "customhead");		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNonNull.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNmZTg4NDVhOGQ1ZTYzNWZiODc3MjhjY2M5Mzg5NWQ0MmI0ZmMyZTZhNTNmMWJhNzhjODQ1MjI1ODIyIn19fQ==");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNonNull.id",1);
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNonNull.name", "&c&mXin vào guild");
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNonNull.slot", 15);	
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.requestJoinNonNull.lore", requestJoinNonNull);
		
		guiFile.get().addDefault("gui.GuestViewGuildInfo.items.back.slot", 22);			
			
		// -------------
			
		guiFile.get().addDefault("gui.viewMembers.rows", 4);
		guiFile.get().addDefault("gui.viewMembers.title", "&8Xem thành viên");
		
		guiFile.get().addDefault("gui.viewMembers.items.member.type", "customhead");		
		guiFile.get().addDefault("gui.viewMembers.items.member.value",
		"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTNhNDdiMTMxZGJjYmMzMGYzY2JlOTY0NzdmYTVlMzcyMjY4MmZhYjk5NWNkMmQyMmY3NWIxNWU5NDgyZmQyMyJ9fX0=");
		guiFile.get().addDefault("gui.viewMembers.items.member.id",1);
		guiFile.get().addDefault("gui.viewMembers.items.member.name", "&6%memberName%");
		guiFile.get().addDefault("gui.viewMembers.items.member.lore", memberStats);		
		
		guiFile.get().addDefault("gui.viewMembers.items.nextPage.slot", 34);
		guiFile.get().addDefault("gui.viewMembers.items.pageInfo.slot", 31);
		guiFile.get().addDefault("gui.viewMembers.items.back.slot", 32);
		guiFile.get().addDefault("gui.viewMembers.items.prevPage.slot", 28);			
		
		
		guiFile.get().options().copyDefaults(true);
		guiFile.save();
	}
}
