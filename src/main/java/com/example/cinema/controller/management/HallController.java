package com.example.cinema.controller.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**影厅管理
 * @author fjj
 * @date 2019/4/12 1:59 PM
 */
@RestController
public class HallController {
    @Autowired
    private HallService hallService;

    @RequestMapping(value = "/hall/all", method = RequestMethod.GET)
    public ResponseVO searchAllHall(){
        return hallService.searchAllHall();
    }

    @PostMapping("/hall/add")
    public ResponseVO addHall(@RequestBody HallForm hallForm){
        return hallService.addHall(hallForm);
    }

    @PostMapping("/hall/update")
    public ResponseVO updateHall(@RequestBody HallForm hallForm){
        return hallService.updateHall(hallForm);
    }

    @PostMapping(value = "/hall/delete/{hallId}")
    public ResponseVO deleteHall(@PathVariable("hallId") int hallId){
        return hallService.deleteHall(hallId);
    }
}
