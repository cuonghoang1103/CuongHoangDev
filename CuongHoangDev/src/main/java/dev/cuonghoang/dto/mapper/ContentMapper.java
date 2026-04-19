package dev.cuonghoang.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.cuonghoang.dto.request.BlogRequestDto;
import dev.cuonghoang.dto.request.ProjectRequestDto;
import dev.cuonghoang.entity.content.Blog;
import dev.cuonghoang.entity.content.Project;

/**
 * Content Mapper
 * Mapper cho Content entities và DTOs
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ContentMapper {

    // Blog mappings
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "viewCount", constant = "0L")
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "publishedAt", ignore = true)
    @Mapping(target = "excerpt", ignore = true)
    @Mapping(target = "readingTime", ignore = true)
    @Mapping(target = "commentCount", constant = "0L")
    @Mapping(target = "likeCount", constant = "0L")
    @Mapping(target = "shareCount", constant = "0L")
    @Mapping(target = "allowComments", constant = "true")
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(source = "seoTitle", target = "metaTitle")
    @Mapping(source = "seoDescription", target = "metaDescription")
    @Mapping(source = "seoKeywords", target = "metaKeywords")
    @Mapping(source = "published", target = "featured")
    Blog toBlogEntity(BlogRequestDto blogRequestDto);

    List<Blog> toBlogEntityList(List<BlogRequestDto> blogRequestDtos);

    // Project mappings
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "viewCount", constant = "0")
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "content", source = "description")
    @Mapping(source = "liveDemoUrl", target = "liveUrl")
    @Mapping(target = "technologies", ignore = true)
    Project toProjectEntity(ProjectRequestDto projectRequestDto);

    List<Project> toProjectEntityList(List<ProjectRequestDto> projectRequestDtos);

    // TODO: Product mappings - implement when ProductRequestDto is created
    // Product toProductEntity(ProductRequestDto productRequestDto);
    // List<Product> toProductEntityList(List<ProductRequestDto>
    // productRequestDtos);

    // TODO: Game mappings - implement when GameRequestDto is created
    // Game toGameEntity(GameRequestDto gameRequestDto);
    // List<Game> toGameEntityList(List<GameRequestDto> gameRequestDtos);
}
