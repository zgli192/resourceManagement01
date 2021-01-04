package com.wyy.ityuyaot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @version:	
 * @Description: 基本元素地址
 * @author: zhao shi jie
 * @date: 2018年8月5日 上午8:45:45
 */
@Controller
@RequestMapping("/developTemplate")
public class DevelopTemplateController {
	
	@GetMapping("/button")
	public ModelAndView button(ModelAndView mv) {
		mv.setViewName("developTemplate/button");
		return mv;
	}
	
	@GetMapping("/form")
	public ModelAndView form(ModelAndView mv) {
		mv.setViewName("developTemplate/form");
		return mv;
	}
	
	@GetMapping("/navigation")
	public ModelAndView navigation(ModelAndView mv) {
		mv.setViewName("developTemplate/navigation");
		return mv;
	}
	
	@GetMapping("/tabs")
	public ModelAndView tabs(ModelAndView mv) {
		mv.setViewName("developTemplate/tabs");
		return mv;
	}
	
	@GetMapping("/panel")
	public ModelAndView panel(ModelAndView mv) {
		mv.setViewName("developTemplate/panel");
		return mv;
	}
	
	@GetMapping("/timeline")
	public ModelAndView timeline(ModelAndView mv) {
		mv.setViewName("developTemplate/timeline");
		return mv;
	}
	
	@GetMapping("/table")
	public ModelAndView table(ModelAndView mv) {
		mv.setViewName("developTemplate/table");
		return mv;
	}
	
	@GetMapping("/assist")
	public ModelAndView assist(ModelAndView mv) {
		mv.setViewName("developTemplate/assist");
		return mv;
	}
	
	@GetMapping("/popup")
	public ModelAndView popup(ModelAndView mv) {
		mv.setViewName("developTemplate/popup");
		return mv;
	}
	
	@GetMapping("/date")
	public ModelAndView date(ModelAndView mv) {
		mv.setViewName("developTemplate/date");
		return mv;
	}
	
	@GetMapping("/page")
	public ModelAndView page(ModelAndView mv) {
		mv.setViewName("developTemplate/page");
		return mv;
	}
	
	@GetMapping("/upload")
	public ModelAndView upload(ModelAndView mv) {
		mv.setViewName("developTemplate/upload");
		return mv;
	}
	
	@GetMapping("/carousel")
	public ModelAndView carousel(ModelAndView mv) {
		mv.setViewName("developTemplate/carousel");
		return mv;
	}
	
	@GetMapping("/badge")
	public ModelAndView badge(ModelAndView mv) {
		mv.setViewName("developTemplate/badge");
		return mv;
	}
	
	@GetMapping("/progressBar")
	public ModelAndView progressBar(ModelAndView mv) {
		mv.setViewName("developTemplate/progressBar");
		return mv;
	}
	
	@GetMapping("/grid")
	public ModelAndView grid(ModelAndView mv) {
		mv.setViewName("developTemplate/grid");
		return mv;
	}
	
	@GetMapping("/icon")
	public ModelAndView icon(ModelAndView mv) {
		mv.setViewName("developTemplate/icon");
		return mv;
	}
	
	@GetMapping("/watermark")
	public ModelAndView watermark(ModelAndView mv) {
		mv.setViewName("developTemplate/watermark");
		return mv;
	}
}
