package com.ly.serviceprice.service;

import com.ly.internalcommon.constant.CommonStatusEnum;
import com.ly.internalcommon.dto.PriceRule;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.request.ForecastPriceDTO;
import com.ly.internalcommon.responese.DirectionResponse;
import com.ly.internalcommon.responese.ForecastPriceResponse;
import com.ly.internalcommon.util.BigDecimalUtils;
import com.ly.serviceprice.mapper.PriceRuleMapper;
import com.ly.serviceprice.remote.ServiceMapClient;
//import com.sun.xml.internal.ws.handler.HandlerProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.DataBufferDouble;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/26 - 09 - 26 - 21:45
 * @Description: com.ly.serviceprice.service
 * @version: 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;
    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {


        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度：" + destLatitude);

        log.info("调用地图服务，查距离时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();

        log.info("距离：" + distance);
        log.info("时长：" + duration);
        
        log.info("读取计价规则");
        
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if(priceRules.size()==0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        
        PriceRule priceRule=priceRules.get(0);

        log.info("根据有距离时长规则计算价格");

        double price = getPrice(distance, duration, priceRule);


        //forecastPriceDTO.setCityCode(cityCode);
        //forecastPriceDTO.setVehicleType(vehicleType);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);

    }
    
    //距离时长规则
    private double getPrice(Integer distance,Integer duration,PriceRule priceRule){

        double price = 0;

        // 起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price,startFare);
        // 里程费
        // 总里程 m
        double distanceMile = BigDecimalUtils.divide(distance,1000);
        // 起步里程
        double startMile = (double)priceRule.getStartMile();
        double distanceSubtract = BigDecimalUtils.substract(distanceMile,startMile);
        // 最终收费的里程数 km
        double mile = distanceSubtract<0?0:distanceSubtract;
        // 计程单价 元/km
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        // 里程价格
        double mileFare = BigDecimalUtils.multiply(mile,unitPricePerMile);
        price = BigDecimalUtils.add(price,mileFare);

        // 时长费
        // 时长的分钟数
        double timeMinute = BigDecimalUtils.divide(duration,60);
        // 计时单价
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();

        // 时长费用
        double timeFare = BigDecimalUtils.multiply(timeMinute,unitPricePerMinute);
        price = BigDecimalUtils.add(price,timeFare);

        BigDecimal priceBigDecimal = new BigDecimal(price);
        priceBigDecimal = priceBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);

        return priceBigDecimal.doubleValue();

        //double price=0;
        //Double startFare=priceRule.getStartFare();
        //BigDecimalUtils.add(price,startFare);
        //
        //
        //BigDecimal distanceDecimal =new BigDecimal(distance);
        //BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        //
        //Integer startMile = priceRule.getStartMile();
        //BigDecimal startMileDecimal=new BigDecimal(startMile);
        //
        //double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
        //Double mile=distanceSubtract<0?0:distanceSubtract;
        //
        //BigDecimal mileDecimal=new BigDecimal(mile);
        //
        //Double unitPricePerMile = priceRule.getUnitPricePerMile();
        //BigDecimal unitPricePerMileDecimal =new BigDecimal(unitPricePerMile);
        //
        //BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        //
        //price=price.add(mileFare);
        ////起步
        ////历程
        ////时长
        //
        //BigDecimal time=new BigDecimal(duration);
        //
        //
        //BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        //
        //Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        //BigDecimal unitPricePerMinuteDecimal=new BigDecimal(unitPricePerMinute );
        //BigDecimal timeFare = timeDecimal.multiply(unitPricePerMinuteDecimal);
        //
        //price=price.add(timeFare);
        //
        //return price.doubleValue();
    }

    //public static void main(String[] args) {
    //    PriceRule priceRule=new PriceRule();
    //    priceRule.setUnitPricePerMile(1.8);
    //    priceRule.setUnitPricePerMinute(0.5);
    //    priceRule.setStartFare(10.0);
    //    priceRule.setStartMile(3);
    //
    //    System.out.println(getPrice(6500,1800,priceRule));
    //}
}
