package backend.command;

import io.eventuate.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteQuestionsCommand implements Command {
    private List<String> ids;
}
