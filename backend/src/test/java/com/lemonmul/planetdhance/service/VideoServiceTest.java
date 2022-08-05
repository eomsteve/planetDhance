package com.lemonmul.planetdhance.service;

import com.lemonmul.planetdhance.entity.*;
import com.lemonmul.planetdhance.entity.tag.Tag;
import com.lemonmul.planetdhance.entity.tag.TagType;
import com.lemonmul.planetdhance.entity.user.User;
import com.lemonmul.planetdhance.entity.video.Video;
import com.lemonmul.planetdhance.entity.video.VideoScope;
import com.lemonmul.planetdhance.repo.MusicRepo;
import com.lemonmul.planetdhance.repo.TagRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class VideoServiceTest {

    @Autowired
    VideoService videoService;
    @Autowired
    MusicRepo musicRepo;
    @Autowired
    TagRepo tagRepo;
    @Autowired
    EntityManager em;

//    @BeforeEach
//    public void before(){
//        List<Tag> tags=new ArrayList<>();
//        tags.add(tagRepo.findByNameAndType("title1",TagType.TITLE));
//        tags.add(tagRepo.findByNameAndType("artist1",TagType.ARTIST));
//        tags.add(tagRepo.findByNameAndType("user1",TagType.NICKNAME));
//        tags.add(tagRepo.findByNameAndType("ko",TagType.NATION));
//
//        for (Tag tag : tags) {
//            VideoTag.createVideoTag(video1,tag);
//        }
//
//        Like.createLike(video1,user1);
//    }

    @Test
    public void findNewestVideoList(){
        //given
//        initdb();

        //when
        int page=0;
        int size=18;
        Music music=musicRepo.findById(4L).get();
        Slice<Video> newestVideoList = videoService.findNewestVideoList(page,size,music, VideoScope.PUBLIC);

        //then
        for (Video video : newestVideoList) {
            System.out.println("video = " + video.getId()+" "+video.getRegDate());
        }

    }

    @Test
    public void findHitLikeVideoList(){
        //given
        initdb();

//        Music music=musicRepo.findById(4L).get();
//        printHitLikeList(0, 5,music);
//        printHitLikeList(1, 5,music);

    }

    private void printHitLikeList(int page, int size,Music music) {
        //when

        Slice<Video> hitLikeVideoList = videoService.findHitLikeVideoList(page, size, music,VideoScope.PUBLIC);

        //then
        for (Video video : hitLikeVideoList) {
            System.out.println("video = " + video.getVideoUrl()+" "+video.getOrderWeight());
        }
    }


    private void initdb() {
        Nation nation1=Nation.createNation("\uD83C\uDDF0\uD83C\uDDF7","ko");
        em.persist(nation1);
        em.persist(Tag.createTag(nation1.getName(), TagType.NATION,"korea img"));

        User user1=User.createUser("email1@xx.xx","user1",null,null,nation1);
        em.persist(user1);
        em.persist(Tag.createTag(user1.getNickname(),TagType.NICKNAME, user1.getImgUrl()));
        User user2=User.createUser("email2@xx.xx","user2",null,null,nation1);
        em.persist(user2);
        em.persist(Tag.createTag(user2.getNickname(),TagType.NICKNAME,user2.getImgUrl()));

        Music music1=Music.createMusic("title1","artist1","album img1","model url1","guide url1","mv url1", LocalDateTime.now());
        em.persist(music1);
        em.persist(Tag.createTag(music1.getArtist(),TagType.ARTIST,"artist img1"));
        em.persist(Tag.createTag(music1.getTitle(),TagType.TITLE, music1.getImgUrl()));
        Music music2=Music.createMusic("title2","artist2","album img2","model url2","guide url2","mv url2",LocalDateTime.now());
        em.persist(Tag.createTag(music2.getArtist(),TagType.ARTIST,"artist img2"));
        em.persist(Tag.createTag(music2.getTitle(),TagType.TITLE, music2.getImgUrl()));
        em.persist(music2);
        Music music3=Music.createMusic("title3","artist2","album img3","model url3","guide url3","mv url3",LocalDateTime.now());
        em.persist(Tag.createTag(music3.getArtist(),TagType.ARTIST,"artist img3"));
        em.persist(Tag.createTag(music3.getTitle(),TagType.TITLE, music3.getImgUrl()));
        em.persist(music3);

        List<Video> videos=new ArrayList<>();
        for(int i=0;i<5;i++){
            Video video = Video.createVideo("video url" + i, VideoScope.PUBLIC, "thumbnail url" + i, user1, music1);
            for(int j=0;j<i;j++){
                video.addHit();
            }
            video.addLikeCnt();
            videos.add(video);
            em.persist(video);
        }
        for(int i=5;i<10;i++){
            Video video = Video.createVideo("video url" + i, VideoScope.PUBLIC, "thumbnail url" + i, user1, music2);
            for(int j=4;j<i;j++){
                video.addHit();
            }            video.addLikeCnt();
            video.addLikeCnt();
            videos.add(video);
            em.persist(video);
        }
        Video video1 = Video.createVideo("video url!!", VideoScope.PRIVATE, "thumbnail url!!", user2, music1);
        videos.add(video1);
        em.persist(video1);
        for(int i=10;i<15;i++){
            Video video = Video.createVideo("video url" + i, VideoScope.PUBLIC, "thumbnail url" + i, user2, music2);
            for(int j=7;j<i;j++){
                video.addHit();
            }
            videos.add(video);
            em.persist(video);
        }
        for(int i=16;i<20;i++){
            Video video = Video.createVideo("video url" + i, VideoScope.PUBLIC, "thumbnail url" + i, user2, music2);
            for(int j=15;j<i;j++){
                video.addHit();
            }
            video.addLikeCnt();
            video.addLikeCnt();
            videos.add(video);
            em.persist(video);
        }
    }

}