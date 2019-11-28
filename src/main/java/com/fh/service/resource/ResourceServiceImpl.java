package com.fh.service.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ServerResponse;
import com.fh.mapper.ResourceMapper;
import com.fh.mapper.RoleResourceMapper;
import com.fh.model.Resource;
import com.fh.model.RoleResource;
import com.fh.model.User;
import com.fh.util.RedisUtil;
import com.fh.util.SessionUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public ServerResponse queryList() {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        List<Resource> resourceList = resourceMapper.selectList(queryWrapper);
        return ServerResponse.success(resourceList);
    }

    @Override
    public void addResource(Resource resource) {
        resourceMapper.insert(resource);
    }

    @Override
    public void deleteResource(List idList) {
        resourceMapper.deleteBatchIds(idList);
    }

    @Override
    public void updateResource(Resource resource) {
        resourceMapper.updateById(resource);
    }

    @Override
    public ServerResponse queryListByRoleId(Integer roleId) {
        //查询全部资源
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        List<Resource> allResourceList = resourceMapper.selectList(queryWrapper);
        //查询该角色拥有的资源
        QueryWrapper<RoleResource> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("roleId",roleId);
        List<RoleResource> roleResourceList = roleResourceMapper.selectList(queryWrapper2);
        //把用户所拥有的资源id放在一个集合
        List<Integer> list = new ArrayList<>();
        roleResourceList.forEach(rr->{
            list.add(rr.getResourceId());
        });
        //两种资源进行比较   如果相等 默认选中
        List<Resource> resourceList = allResourceList.stream().filter(x -> {
            if (list.contains(x.getId())) {
                x.setChecked(true);
            }
            return true;
        }).collect(Collectors.toList());

        return ServerResponse.success(resourceList);
    }

    @Override
    public ServerResponse getCurrentUserResource(HttpServletRequest request,HttpServletResponse response) {
        String sessionId = SessionUtil.getSessionId(request, response);
        //User user = (User) request.getSession().getAttribute(SystemConstant.LOGGIN_CURRENT_USER);
        String resource1 = RedisUtil.get(SystemConstant.LOGGIN_CURRENT_USER + sessionId);
        User user = JSON.parseObject(resource1, User.class);
        List<Resource> resourceList =  resourceMapper.getCurrentUserResource(user.getId());
        return ServerResponse.success(resourceList);
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);
      //  list.forEach(x->System.out.println(x));
       // list.stream().filter(x->x>3).forEach(x->System.out.println(x));
        list.stream().filter(x->{
            boolean re=true;
            if(x>3){
                re=false;
            }
            return  re;
        });
     //   list.stream().skip(2).limit(2).forEach(x->System.out.println(x));
      //  long count = list.stream().count();
       // System.out.println(count);

    }
}
