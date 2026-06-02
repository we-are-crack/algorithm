select info.item_id, info.item_name, info.rarity
from item_info info
join item_tree tree
    on tree.parent_item_id in (select item_id from item_info where rarity = 'RARE')
    and info.item_id = tree.item_id
order by info.item_id desc