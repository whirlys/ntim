package com.whirly.controller.publicer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.whirly.enums.NoticeLever;
import com.whirly.enums.NoticeType;
import com.whirly.form.DelayForm;
import com.whirly.form.FileSearchForm;
import com.whirly.form.FormForm;
import com.whirly.form.FormSearchForm;
import com.whirly.form.NoticeForm;
import com.whirly.form.NoticeMessageForm;
import com.whirly.form.NoticeSearchForm;
import com.whirly.form.messageSearchForm;
import com.whirly.imserver.model.PushMessageBody;
import com.whirly.model.Field;
import com.whirly.model.File;
import com.whirly.model.Form;
import com.whirly.model.Message;
import com.whirly.model.Notice;
import com.whirly.model.Publicer;
import com.whirly.model.Record;
import com.whirly.model.Supplement;
import com.whirly.model.Timeline;
import com.whirly.model.User;
import com.whirly.service.FieldService;
import com.whirly.service.FileService;
import com.whirly.service.FormService;
import com.whirly.service.MessageService;
import com.whirly.service.NoticeService;
import com.whirly.service.PushService;
import com.whirly.service.RecordService;
import com.whirly.service.SupplementService;
import com.whirly.service.TimelineService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.FriendsListVO;
import com.whirly.vo.MessageVO;
import com.whirly.vo.NoticeIsReadVo;
import com.whirly.vo.ReceiverFormVO;
import com.whirly.vo.ReceiverVO;
import com.whirly.vo.RecordVO;

import junit.framework.Test;

@Controller
@RequestMapping("publicer")
public class PublicerControler {

	@Autowired
	private PushService pushService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private UserService userService;

	@Autowired
	private TimelineService timelineService;

	@Autowired
	private SupplementService supplementService;

	@Autowired
	private FormService formService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private FileService fileService;

	@Autowired
	private RecordService recordService;

	@Autowired
	private MessageService messageService;

	private ObjectMapper mapper = new ObjectMapper();

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("")
	public String index(Model model, HttpSession session) {
		model.addAttribute("jsessionid", session.getId());
		return "publicer/index";
	}

	@RequestMapping("main")
	public String main(Model model) {
		return "publicer/main";
	}

	@RequestMapping("toNotices")
	public String toNotices() {
		return "publicer/notices";
	}

	@RequestMapping("notices")
	@ResponseBody
	public Map<String, Object> notices(NoticeSearchForm form, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		form.setUserId(user.getUserId());
		PageInfo<Notice> pageInfo = noticeService.selectBySearchForm(form);
		List<Notice> list = pageInfo.getList();
		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}

	@RequestMapping("toAddNotice")
	public String toAddNotice(HttpSession session, Model model) {
		Publicer publicer = (Publicer) session.getAttribute("publicer");
		List<FriendsListVO> studentGroups = userService.selectFriendsListVOByPublicerIdSimple(publicer.getPublicerId());
		model.addAttribute("studentGroups", studentGroups);
		return "publicer/noticeForm";
	}

	@RequestMapping("test")
	@ResponseBody
	public Msg Test(HttpSession session) {
		User user = (User) session.getAttribute("user");
		String title = "来自 " + user.getUsername() + " 的新通知";
		PushMessageBody pushMessageBody = new PushMessageBody();
		pushMessageBody.setTitle("测试的通知标题");
		pushMessageBody.setSampleContent("这是一条通知...");
		List<Integer> toList = new LinkedList<>();
		toList.add(8776);
		toList.add(8777);
		toList.add(8778);
		pushService.pushTimeline(user.getUserId(), toList, title, pushMessageBody);
		return Msg.success();
	}

	@RequestMapping("addNotice")
	@ResponseBody
	public Msg addNotice(NoticeForm form, HttpSession session) {
		Publicer publicer = (Publicer) session.getAttribute("publicer");
		User user = (User) session.getAttribute("user");
		form.setPublicer(user.getUsername() + publicer.getDuty());
		form.setUserId(user.getUserId());
		Notice notice = new Notice();
		notice.setLever(form.getLever());
		notice.setType(form.getType());
		notice.setTitle(form.getTitle());
		notice.setPublicer(form.getPublicer());
		notice.setContent(form.getContent());
		notice.setUserId(form.getUserId());
		int status = noticeService.insert(notice);
		List<Timeline> timelines = new LinkedList<Timeline>();
		for (Integer userId : form.getReceivers()) {
			Timeline timeline = new Timeline();
			timeline.setIsRead(false);
			timeline.setNoticeId(notice.getNoticeId());
			timeline.setUserId(userId);
			timelines.add(timeline);
		}
		status = timelineService.batchInsert(timelines);

		// 推送
		try {
			String title = "来自 " + user.getUsername() + " 的新通知";
			PushMessageBody pushMessageBody = new PushMessageBody();
			pushMessageBody.setTitle(notice.getTitle());
			pushMessageBody.setSampleContent(notice.getContent().substring(0, 30) + "...");
			List<Integer> toList = Arrays.asList(form.getReceivers());
			pushService.pushTimeline(user.getUserId(), toList, title, pushMessageBody);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Msg.success();
	}

	@RequestMapping(value = "notice/{noticeId}")
	public String detail(@PathVariable(name = "noticeId") Integer noticeId, Model model) {
		Notice notice = noticeService.selectById(noticeId);
		List<Supplement> supplements = supplementService.selectByNoticeId(noticeId);
		List<NoticeIsReadVo> isReadVos = timelineService.selectIsReadNumByNoticeId(noticeId);
		Integer readNum = 0, notReadNum = 0;
		for (NoticeIsReadVo vo : isReadVos) {
			if (vo.isRead() == true) {
				readNum = vo.getNum();
			} else {
				notReadNum = vo.getNum();
			}
		}
		model.addAttribute("notice", notice);
		model.addAttribute("supplements", supplements);
		model.addAttribute("readNum", readNum);
		model.addAttribute("notReadNum", notReadNum);
		return "publicer/noticeDetail";
	}

	@RequestMapping(value = "showReceiver/{noticeId}")
	public String showReceiver(@PathVariable(name = "noticeId") Integer noticeId, Model model) {
		List<ReceiverVO> receivers = userService.selectReceiverByNoticeId(noticeId);

		ReceiverVO readList = null, notReadList = null;
		for (ReceiverVO vo : receivers) {
			if (vo.getIsRead() == true) {
				readList = vo;
			} else {
				notReadList = vo;
			}
		}
		model.addAttribute("readList", readList);
		model.addAttribute("notReadList", notReadList);
		return "publicer/showReceiver";
	}

	@RequestMapping(value = "addSupplement/{noticeId}", method = RequestMethod.GET)
	public String addSupplement(@PathVariable(name = "noticeId") Integer noticeId, Model model) {
		return "publicer/supplement";
	}

	@RequestMapping(value = "addSupplement/{noticeId}", method = RequestMethod.POST)
	public String supplement(@PathVariable(name = "noticeId") Integer noticeId, String content, Model model) {
		if (null == content || content.equals("")) {
			model.addAttribute("message", "内容不能为空！");
		} else {
			boolean isExist = noticeService.exist(noticeId);
			if (isExist) {
				Supplement supplement = new Supplement();
				supplement.setContent(content);
				supplement.setNoticeId(noticeId);
				supplementService.insert(supplement);
				model.addAttribute("message", "添加补充说明成功！");
			} else {
				// model.addAttribute("content", content);
				model.addAttribute("message", "该通知不存在！");
			}
		}
		return "publicer/supplement";
	}

	@RequestMapping("toForms")
	public String toForm(HttpSession session, Model model) {
		Publicer publicer = (Publicer) session.getAttribute("publicer");
		return "publicer/forms";
	}

	@RequestMapping("forms")
	@ResponseBody
	public Map<String, Object> forms(FormSearchForm form, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		form.setUserId(user.getUserId());
		PageInfo<Form> pageInfo = formService.selectBySearchForm(form);
		List<Form> list = pageInfo.getList();
		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}

	@RequestMapping("toAddForm")
	public String toAddForm(HttpSession session, Model model) {
		Publicer publicer = (Publicer) session.getAttribute("publicer");
		List<FriendsListVO> studentGroups = userService.selectFriendsListVOByPublicerIdSimple(publicer.getPublicerId());
		model.addAttribute("studentGroups", studentGroups);
		return "publicer/formForm";
	}

	@RequestMapping(value = "addForm", method = RequestMethod.POST)
	@ResponseBody
	public Msg addForm(HttpSession session, @RequestBody FormForm formForm, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Publicer publicer = (Publicer) session.getAttribute("publicer");
		User user = (User) session.getAttribute("user");
		Form form = new Form();
		form.setDeadline(formForm.getDeadline());
		form.setDescription(formForm.getDescription());
		form.setFormName(formForm.getFormName());
		form.setReceivers(JSON.toJSONString(formForm.getReceivers()));
		form.setUserId(publicer.getUserId());
		formService.insert(form);
		List<Field> fields = formForm.getFieldItems();
		for (Field f : fields) {
			f.setFormId(form.getFormId());
			f.setConstraint(JSON.toJSONString(f.getConstraint()));
		}
		fieldService.batchInsert(fields);
		List<Record> records = new LinkedList<Record>();
		for (Integer userId : formForm.getReceivers()) {
			Record record = new Record();
			record.setFilled(false);
			record.setFormId(form.getFormId());
			record.setUserId(userId);
			record.setValue("{}");
			records.add(record);
		}
		recordService.batchInsert(records);

		Notice notice = new Notice();
		notice.setLever((short) NoticeLever.GENERAL.getLever());
		notice.setPublicer(user.getUsername() + publicer.getDuty());
		notice.setType(NoticeType.FORM.getName());
		notice.setTitle("关于填写 " + formForm.getFormName() + "的通知");
		notice.setUserId(user.getUserId());
		notice.setContent(formForm.getDescription() + "\n\n 请点击<a href='" //
				+ request.getContextPath() + "/student/form/" + form.getFormId() //
				+ "' target='_blank' style='color: red;'> 表单填写地址 </a> 或复制 " //
				+ "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/student/form/" + form.getFormId() + " 到浏览器中打开");//

		int status = noticeService.insert(notice);
		List<Timeline> timelines = new LinkedList<Timeline>();
		for (Integer userId : formForm.getReceivers()) {
			Timeline timeline = new Timeline();
			timeline.setIsRead(false);
			timeline.setNoticeId(notice.getNoticeId());
			timeline.setUserId(userId);
			timelines.add(timeline);
		}
		status = timelineService.batchInsert(timelines);
		return Msg.success();
	}

	@RequestMapping(value = "form/{formId}")
	public String formDetail(Model model, @PathVariable("formId") Integer formId)
			throws JsonParseException, JsonMappingException, IOException {
		Form form = formService.selectById(formId);
		List<RecordVO> records = recordService.selectByFormId(formId);

		for (RecordVO vo : records) {
			Map<String, Object> m = mapper.readValue(vo.getValue(), Map.class);
			vo.setValueMap(m);
		}
		Integer readNum = 0, notReadNum = 0;
		readNum = recordService.countNum(true); // 已填人数
		notReadNum = recordService.countNum(false); // 未填人数
		model.addAttribute("form", form);
		model.addAttribute("records", records);
		model.addAttribute("readNum", readNum);
		model.addAttribute("notReadNum", notReadNum);
		return "publicer/formDetail";
	}

	@RequestMapping(value = "formDelay/{formId}", method = RequestMethod.GET)
	public String formDelay(Model model, @PathVariable("formId") Integer formId) {
		Form form = formService.selectById(formId);
		model.addAttribute("form", form);
		return "publicer/formDelay";
	}

	@RequestMapping(value = "postDelay/{formId}", method = RequestMethod.POST)
	@ResponseBody
	public Msg formDelaypost(Model model, @PathVariable("formId") Integer formId, @RequestBody DelayForm delayForm) {
		Form form = formService.selectById(formId);
		if (null == form) {
			Msg msg = Msg.error();
			msg.setMsg("表单不存在");
			return msg;
		}
		delayForm.setFormId(formId);
		formService.updateDeadline(delayForm);
		return Msg.success();
	}

	@RequestMapping(value = "toExcel/{formId}")
	public void toExcel(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("formId") Integer formId) throws JsonParseException, JsonMappingException, IOException {
		// 查数据
		Form form = formService.selectById(formId);
		List<RecordVO> records = recordService.selectByFormId(formId);

		for (RecordVO vo : records) {
			Map<String, Object> m = mapper.readValue(vo.getValue(), Map.class);
			vo.setValueMap(m);
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 设置标头
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("院系");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("年级");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("专业");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("班级");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("学号");
		cell.setCellStyle(style);

		int i = 6;

		HSSFCellStyle leftstyle = wb.createCellStyle();
		leftstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		leftstyle.setFont(font);
		for (Field field : form.getFields()) {
			cell = row.createCell(i);
			cell.setCellValue(field.getName());
			cell.setCellStyle(leftstyle);
			i = i + 1;
		}
		int rownum = 1;
		// 加入数据
		for (RecordVO vo : records) {
			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			cell.setCellValue(vo.getClasss().getDepartment().getName());
			cell = row.createCell(1);
			cell.setCellValue(vo.getClasss().getGrade());
			cell = row.createCell(2);
			cell.setCellValue(vo.getClasss().getProfession());
			cell = row.createCell(3);
			cell.setCellValue(vo.getClasss().getClassNumber());
			cell = row.createCell(4);
			cell.setCellValue(vo.getUsername());
			cell = row.createCell(5);
			cell.setCellValue(vo.getAccount());

			int cellnum = 6;
			for (Field field : form.getFields()) {
				cell = row.createCell(cellnum);
				String value = (String) vo.getValueMap().get(field.getName());
				if (field.getRequired() == true && value.equals("")) {
					cell.setCellValue(field.getDefaultValue());
				} else {
					cell.setCellValue(value);
				}
				cellnum = cellnum + 1;
				System.out.println(value);
			}
			rownum = rownum + 1;
		}
		// 下载
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String fileName = form.getFormName() + sdf.format(date) + ".xls";
			response.reset();
			response.setContentType("application/msexcel;");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes(), "iso-8859-1"));
			wb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "showReceiverForm/{formId}")
	public String showReceiverForm(@PathVariable(name = "formId") Integer formId, Model model) {
		List<ReceiverFormVO> receivers = userService.selectReceiverByFormId(formId);

		ReceiverFormVO filledList = null, notfilledList = null;
		for (ReceiverFormVO vo : receivers) {
			if (vo.getFilled() == true) {
				filledList = vo;
			} else {
				notfilledList = vo;
			}
		}

		model.addAttribute("filledList", filledList);
		model.addAttribute("notfilledList", notfilledList);
		return "publicer/showReceiverForm";
	}

	@RequestMapping("toFiles")
	public String toFiles(HttpSession session, Model model) {
		return "publicer/files";
	}

	@RequestMapping("files")
	@ResponseBody
	public Map<String, Object> files(FileSearchForm form, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<File> pageInfo = fileService.selectBySearchForm(form);
		List<File> list = pageInfo.getList();
		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}

	@RequestMapping("toAddFile")
	public String toAddFile(HttpSession session, Model model) {
		return "publicer/fileForm";
	}

	@RequestMapping(value = "addFile", method = RequestMethod.POST)
	@ResponseBody
	public Msg addFile(@RequestBody File file, HttpSession session, Model model) {
		Publicer publicer = (Publicer) session.getAttribute("publicer");
		file.setUserId(publicer.getUserId());
		fileService.insert(file);
		return Msg.success();
	}

	@RequestMapping("noticeMessage")
	@ResponseBody
	public Msg noticeMessage(messageSearchForm searchForm) {
		List<MessageVO> list = messageService.getNoticeMessages(searchForm);
		Msg msg = Msg.success();
		msg.add("messages", list);
		return msg;
	}

	@RequestMapping("addNoticeMessage")
	@ResponseBody
	public Msg addNoticeMessage(@RequestBody NoticeMessageForm form, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Message message = new Message();
		message.setContent(form.getContent());
		message.setMsgFrom(user.getUserId());
		message.setMsgTo(form.getNoticeId());
		message.setMsgType((short) 3);
		messageService.insertNoticeMessage(message);
		return Msg.success();
	}
}
