# RelEval-Expansion
This is a [PlaceholderAPI](http://placeholderapi.com/) expansion that allows you to evaluate other placeholders through the context of a Relational Placeholder.
It only provides relation placeholders, so the plugin you'll use them in should be supporting relation placeholders from PlaceholderAPI.

Supports using any PlaceholderAPI placeholder in any of the placeholder's fields, but the placeholder should be inside of `{}` instead of `%%`.

## Placeholders

### `%rel_releval_<player/viewed>:<bracketed-placeholder>%` <br />
Outputs the evaluation of `<bracketed-placeholder>` in the context of `<player/viewed>` <br />

> #### `<player/viewed>` 
This field is used to pick which player should the following `<bracketed-placeholder>` be evaluated as.

> `player` is the client's player (the one sending the request). <br />
> `viewed` is the second player (the one being viewed by the client). <br />
<br />

> #### `<bracketed-placeholder>` 
The placeholder what will be evaluated from the context of the latter `<player/viewed>`. <br />
<br />
<br />

### `%rel_releval_<player/viewed>:<bracketed-placeholder-returning-yes/no>_<player/viewed>:<result-yes>%` <br />
Depending on the result of the evaluation of `<player/viewed>:<bracketed-placeholder-returning-yes/no>`, the output can be as follows:
> if the result is `yes`, then outputs the evaluation of `<player/viewed>:<result-yes>`   <br />
> if the result is `no`, then outputs ''   <br />
> if the result is any other value than `yes/no`, then outputs `releval_invalid_input:<value>`  <br />
<br />
> 
> #### `<player/viewed>`
This field is used to pick which player should the following field be evaluated as.

> `player` is the client's player (the one sending the request). <br />
> `viewed` is the second player (the one being viewed by the client). <br />
<br />

> #### `<bracketed-placeholder-returning-yes/no>`
The placeholder what will be evaluated from the context of the latter `<player/viewed>`. <br />
The bracketed placeholder must result on a yes/no string, otherwise the output will be `releval_invalid_input:<value>` <br />
<br />

> #### `<result-yes>`
The placeholder what will be evaluated from the context of the latter `<player/viewed>` in the case that the `<bracketed-placeholder-returning-yes/no>` returns `yes`. <br />
<br />
<br />

### `%rel_releval_<player/viewed>:<bracketed-placeholder-returning-yes/no>_<player/viewed>:<result-yes>_<player/viewed>:<result-no>%` <br />
Depending on the result of the evaluation of `<player/viewed>:<bracketed-placeholder-returning-yes/no>`, the output can be as follows:
> if the result is `yes`, then outputs the evaluation of `<player/viewed>:<result-yes>`   <br />
> if the result is `no`, then outputs the evaluation of `<player/viewed>:<result-no>`   <br />
> if the result is any other value than `yes/no`, then outputs `releval_invalid_input:<value>`  <br />
<br />

> #### `<player/viewed>`
This field is used to pick which player should the following field be evaluated as.

> `player` is the client's player (the one sending the request). <br />
> `viewed` is the second player (the one being viewed by the client). <br />
<br />

> #### `<bracketed-placeholder-returning-yes/no>`
The placeholder what will be evaluated from the context of the latter `<player/viewed>`. <br />
The bracketed placeholder must result on a yes/no string, otherwise the output will be `releval_invalid_input:<value>` <br />
<br />

> #### `<result-yes>`
The placeholder what will be evaluated from the context of the latter `<player/viewed>` in the case that the `<bracketed-placeholder-returning-yes/no>` returns `yes`. <br />
<br />

> #### `<result-no>`
The placeholder what will be evaluated from the context of the latter `<player/viewed>` in the case that the `<bracketed-placeholder-returning-yes/no>` returns `no`. <br />
<br />

(If you have any suggestions or issues, feel free to let me know by opening a new issue [here](https://github.com/Gabo6480/RelEval-Expansion/issues))

## Example

### Displaying a hidden tooltip based on the client's permissions
Using RelEval in conjunction with the [LuckPerms](https://github.com/PlaceholderAPI/PlaceholderAPI/wiki/Placeholders#luckperms) expansion. <br />
```yml
%rel_releval_player:{luckperms_check_permission_tooltip.displayhidden}_player:§5this is a hidden tooltip, for you eyes only.%
```
<br />With this and the [TAB](https://github.com/NEZNAMY/TAB) plugin you could, for example, display hidden information in any player's tag depending on the Client's permission. <br />

> Keep in mind that in this example, since `<result-yes>` is a static string the latter `<player/viewed>` has no impact.
> However, it is still required due to syntax.


# Download
This expansion hasn't been uploaded to the eCloud yet.
<!--You can download this expansion automatically using PAPI download commands:

> ```
> /papi ecloud download RelEval
> /papi reload
> ```

Or you can download it manually from the [eCloud](https://api.extendedclip.com/expansions/releval/) and then put it in the `expansions` folder (Path: `/plugins/PlaceholderAPI/expansions/`)
-->