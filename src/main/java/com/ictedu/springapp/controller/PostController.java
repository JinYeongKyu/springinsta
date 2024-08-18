package com.ictedu.springapp.controller;

import com.ictedu.springapp.model.MemberDTO;
import com.ictedu.springapp.model.PostDTO;
import com.ictedu.springapp.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    private final Path fileStorageLocation;

    @Autowired
    public PostController() throws IOException {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    @PostMapping("/create")
    public void create(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("content") String content,
            @RequestParam("location") String location,
            @RequestParam("hashtag") String hashtag,
            HttpSession session) throws IOException {

        MemberDTO member = (MemberDTO) session.getAttribute("member");
        String imageUrl = saveFile(imageFile);
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(member.getId());
        postDTO.setContent(content);
        postDTO.setLocation(location);
        postDTO.setHashtag(hashtag);
        postDTO.setImgUrl(imageUrl);
        postService.createPost(postDTO);
    }

    @GetMapping("/post")
    public List<PostDTO> getPosts() {
        return postService.getAllPosts();
    }

    private String saveFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IOException("File name cannot be null or empty");
        }

        // 파일 이름 중복을 피하기 위해 UUID를 추가
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), targetLocation);

        return "/uploads/" + uniqueFileName;
    }

}
