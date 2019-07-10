package cz.vojtechvosmik.soon.interfaces

import cz.vojtechvosmik.soon.models.Event

interface EditEventInterface {

    fun onEventEdited(newEvent: Event)
}