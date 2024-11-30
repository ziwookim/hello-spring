package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

//    private final MemberService = new MemberService;
    // 다른 도메인이나 여러 서버에서 동시에 계속 호출하는 경우, 계속 새로운 객체로 인식될 수 있음.
    private final MemberService memberService;
//
//    // 생성자 주입 *가장 권장 @Autowired
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

    // 필드 주입
//    @Autowired private MemberService memberService;
//      중간에 변경할 수 있는 방법이 따로 없음.

    // Setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    // setMemberService가 public으로 노출 되어 있어 문제 가능성 생김.

    @GetMapping("/members/new")
    public String createForm() {
        System.out.println("get");
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        System.out.println("post");
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
