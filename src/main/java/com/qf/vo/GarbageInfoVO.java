package com.qf.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/8/13.
 */
@Data
public class GarbageInfoVO {
    long log_id;
    int result_num;
    List<ItemInfoVO> result = new ArrayList<ItemInfoVO>();
}
