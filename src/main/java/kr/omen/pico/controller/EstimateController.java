package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.EstimateDTO;
import kr.omen.pico.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "/estimate/addGE")
    public EstimateDTO.Create insertGlobalEstimate(@RequestBody EstimateDTO.Create dto){
        dto.setStatus("1");
        return estimateService.createGlobalEstimate(dto);
    }

    @PostMapping("/estimate/addPE/{pId}")
    public EstimateDTO.Create insertPickedEstimate(@RequestBody EstimateDTO.Create dto, @PathVariable Long pId){
        dto.setStatus("2");
        estimateService.createPickedEstimate(dto,pId);
        return dto;
    }
}
