package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.EstimateDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class EstimateController {

    @Autowired
    private EstimateService estimateService;

    //Oauth 토큰 및 세션 등에 User 객체가 저장되지 않은상태로 수동으로 사용자 idx/ 카테고리 idx 입력받는 방식으로
    //진행한 test 코드. 추후에 메소드가 완성되려면
    //1. 세션이나 토큰 등 어딘가에 저장되어있는 User 정보를 불러와서 User 객체 set
    //2. Category를 radiobutton 등으로 체크해서 String으로 받아오고 그에 해당하는 카테고리 idx 기준으로 category 객체 생성 후 set
    //3. 대충 이런방식으로 생성된 estimate를 기준으로 활동 시/구 가 일치하는 작가들(임시)에게 해당 견적서 정보 발송하는 방식으로 구축하면
    //될 것으로 생각됨.

    //견적요청
    @PostMapping(value = "/estimate/add")
    public ResponseDTO.EstimateResponse insertGlobalEstimate(@RequestBody EstimateDTO.Create pdto){
        //글로벌 견적일 경우
        if(pdto.getPidx()==null){
            pdto.setStatus("1");
            return estimateService.createGlobalEstimate(pdto);
        }
        //작가지정일 경우
        else if(pdto.getPidx()!=null){
            pdto.setStatus("2");
            return estimateService.createPickedEstimate(pdto);
        }
        return null;
    }

    //유저가 요청한 견적서 목록 출력
    @GetMapping("/estimate/getUserAll/{userId}")
    public List<ResponseDTO.SimpleCard> getUserAllEstimate(@PathVariable Long userId){
        return estimateService.getUserAllEstimate(userId);
    }

    //견적서 목록중 하나 클릭시 해당 견적서 상세 내용 및 지원한 작가목록(is_applied가 true인)출력
    @GetMapping("/estimate/getUserOne/{estimateId}")
    public ResponseDTO.DetailResponse getUserOneEstimate(@PathVariable Long estimateId){
        return estimateService.getUserOneEstimate(estimateId);
    }

    //작가가 받은(할당된) 견적서 목록 출력
    @GetMapping("/estimate/getPhotographerAll/{pId}")
    public List<ResponseDTO.SimpleCard> getPhotographerAllEstimate(@PathVariable Long pId){
        return estimateService.getPhotographerAllEstimate(pId);
    }

    //사용자가 요청한 견적서 취소(삭제)(연결되어있는 Apply리스트들은 status의 상태를 7로 변경)
    @DeleteMapping("/estimate/cancelEstimate/{estimateId}")
    public String deleteMyEstimate(@PathVariable Long estimateId){
        boolean cancel = estimateService.deleteMyEstimate(estimateId);
        if(cancel){
            return "삭제성공";
        }else{
            return "삭제실패";
        }
    }

    @CrossOrigin
    @GetMapping("/test")
    public String teetResponse() {
        System.out.println("요청");
        return "호출 성공";
    }
}
