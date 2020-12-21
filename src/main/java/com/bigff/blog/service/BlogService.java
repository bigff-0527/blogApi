package com.bigff.blog.service;

        import com.bigff.blog.entity.util.PageRequest;
        import com.bigff.blog.entity.util.PageResult;

        import java.util.List;

public interface BlogService {



  PageResult getBlogList(PageRequest pageRequest);
}