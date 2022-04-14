import java.util.function.DoubleBinaryOperator

//Заглушка для Note, чтобы иметь id владельца заметок и приватность профиля.

class Owner {
    val id: Int = 100000
    val private: Boolean = true
}

object OwnerService {
    var ownerList = mutableListOf<Owner>()

    fun getId(): Int {
        val owner = Owner()
        ownerList.add(owner)
        return owner.id
    }

    fun isPrivate(ownerId: Int): Int {
        var private: Int = -1
        for(owner in ownerList) {
            if(owner.id == ownerId) {
                private = if(owner.private) {
                    0
                }else 1
            }else error("Owner not found exception")
        }
        return private
    }
}
