package mukhammed.dto.response;

import lombok.Builder;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record SimpleResponse(String status, String massage) {}
