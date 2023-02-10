package com.example.financialfinalproject.controller.restController;

import com.example.financialfinalproject.domain.dto.TradingDiaryDto;
import com.example.financialfinalproject.domain.dto.TradingDiaryListDto;
import com.example.financialfinalproject.domain.request.DiaryPutRequest;
import com.example.financialfinalproject.domain.response.MyCoinCntResponse;
import com.example.financialfinalproject.domain.response.Response;
import com.example.financialfinalproject.service.TradingDiaryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diary")
public class TradingDiaryRestController {

    private final TradingDiaryService tradingDiaryService;

    @ApiOperation(value = "매매일지 메모 수정")
    @PutMapping("/edit/{id}")
    public TradingDiaryDto edit(@ApiIgnore Authentication authentication, @PathVariable Long id, @RequestBody DiaryPutRequest request) {
        log.info("comment:{}", request.getComment());
        String email = authentication.getName();
        TradingDiaryDto tradingDiaryDto = tradingDiaryService.edit(id, request.getComment(), email);
        return tradingDiaryDto;
    }

//    @ApiOperation(value = "매매일지 리스트")
//    @GetMapping("/list")
//    public List<TradingDiaryListDto> list(Authentication authentication) {
//        String email = authentication.getName();
//        List<TradingDiaryListDto> tradingDiaryList = tradingDiaryService.listOf(email);
//        return tradingDiaryList;
//    }

    @ApiOperation(value = "보유중인 코인 개수 조회")
    @GetMapping("/count")
    public ResponseEntity<Response<MyCoinCntResponse>> getCnt(Authentication authentication) {
        String email = authentication.getName();
        MyCoinCntResponse coinCnt = tradingDiaryService.getCoins(email);
        return ResponseEntity.ok().body(Response.success(coinCnt));
    }

//    @ApiOperation(value = "날짜 조건 조회")
//    @GetMapping("/search")
//    public ResponseEntity<Response<List<TradingDiaryListDto>>> findByCond(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Authentication authentication) {
//        log.info("startDate : {}", startDate);
//        log.info("endDate : {}", endDate);
//        String email = authentication.getName();
//        List<TradingDiaryListDto> listByCond = tradingDiaryService.findListByCond(email, startDate, endDate);
//        log.info("search cnt : {}", listByCond.size());
//        return ResponseEntity.ok().body(Response.success(listByCond));
//    }

//    @ApiOperation(value = "시간 별 수익률 조회")
//    @GetMapping("/revenue")
//    public ResponseEntity<Response<Double>> find(@RequestParam(required = false) int startTime, @RequestParam(required = false) int endTime, Authentication authentication) {
//        String email = authentication.getName();
//        Double avg = tradingDiaryService.avgRevenueByTime(email, startTime, endTime);
//        log.info("평균 수익률 : {}", avg);
//        return ResponseEntity.ok().body(Response.success(avg));
//    }


    @ApiOperation(value = "매매일지 삭제")
    @DeleteMapping("/delete")
    public TradingDiaryDto delete(@ApiIgnore Authentication authentication, Long id) {
        String email = authentication.getName();
        TradingDiaryDto tradingDiaryDto = tradingDiaryService.delete(email,id);
        return tradingDiaryDto;
    }

}
