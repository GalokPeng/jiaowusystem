package com.qimozuoye.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimozuoye.mapper.SclassMapper;
import com.qimozuoye.pojo.GuestUser;
import com.qimozuoye.pojo.Sclass;
import com.qimozuoye.pojo.Student;
import com.qimozuoye.service.SclassService;
import com.qimozuoye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class SclassController {
//    @Autowired
//    UserMapper userMapper;

    @Autowired
    private SclassService sclassService;

    @Autowired
    private SclassMapper sclassMapper;


    @RequestMapping("/clas")
    public String list(@RequestParam(value = "pg",defaultValue = "1")Integer pg,Model model){

        Page<Sclass> sclassPage = new Page<>(pg,5);
        Page<Sclass> page = sclassService.page(sclassPage,null);
        model.addAttribute("page",page);

        return "cla/clalist";
    }

//    @GetMapping("/clas")
//    public String toClas(Model model){
////        Collection<GuestUser> guestUserList = userMapper.findAll();
//        Collection<Sclass> sclasses = sclassService.getSclasses();
//        model.addAttribute("clas",sclasses);
//        return "cla/clalist";
//    }
    @GetMapping("/cla")
    public String toAdd(Model model){
        return "cla/add";
    }
    //添加用户
    @PostMapping("/cla")
    public String addCla(Sclass sclass){
        System.out.println("add=>"+sclass);
        sclassService.save(sclass);
        return"redirect:/clas";
    }

    @GetMapping("/cla/{id}")
    public String toUpdateCla(@PathVariable("id") int id ,Model model){
        Sclass sclass = sclassService.get(id);
        model.addAttribute("cla",sclass);
        return "cla/update";
    }
    //修改用户
    @PostMapping("/updateCla")
    public String updateCla(Sclass sclass){
        System.out.println("updatecla=>"+sclass);
        sclassService.update(sclass);
        return "redirect:/clas";
    }

    //删除用户
    @GetMapping("/delecla/{id}")
    public String toDelete(@PathVariable("id") int id){
        sclassService.delete(id);
        return "redirect:/clas";
    }


    //查询班级
    @PostMapping("/selecla")
    public String toSeleCla(int id, Model model) {


//        Student student1 =null;
        try{
            Sclass sclass = sclassService.get(id);
            if (sclass == null){
                System.out.println(sclass);
                return "cla/selecla";
            }else {
//                System.out.println(student);
                model.addAttribute("selecla", sclass);
                Collection<Sclass> sclasses = sclassMapper.getSclasses();
                model.addAttribute("sclasses", sclasses);
                sclassService.get(id);
                return "cla/selecla";
            }
        }catch (IllegalStateException e){
            return "cla/selecla";

        }

    }
}
