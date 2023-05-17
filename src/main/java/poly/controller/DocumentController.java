package poly.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.DocumentDao;
import poly.dao.StaffDao;
import poly.entity.Document;
import poly.entity.Staff;

@Transactional
@Controller
@RequestMapping("don-tu")
public class DocumentController {
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private StaffDao staffDao;
	
	@RequestMapping("danh-sach")
	public String ShowList(ModelMap model, 
			@RequestParam(value = "id", required = false) String id
			) {
		if (id != null) {
			Document document = documentDao.get(Integer.parseInt(id));
			model.addAttribute("document", document);
		}
		List<Document> list = documentDao.getAll();
		model.addAttribute("documents", list);
		return "documentList";
	}
	
	@RequestMapping("them-moi")
	public String add(ModelMap model
			) {
		List<Staff> creators = staffDao.getAll();
		Document document = new Document();
		model.addAttribute("creators", creators);
		model.addAttribute("document", document);
		return "addDocument";
	}
	
}
