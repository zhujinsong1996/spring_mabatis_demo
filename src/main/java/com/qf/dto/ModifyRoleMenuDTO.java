package com.qf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/8/2.
 */
@Getter
@Setter
@ToString
public class ModifyRoleMenuDTO {
    int roleId;
    List<Integer> menuIds;


}
