package net.squanchy.venue

import io.reactivex.Observable
import net.squanchy.service.firebase.FirebaseAuthService
import net.squanchy.service.firestore.FirestoreDbService
import net.squanchy.service.firestore.toVenue
import net.squanchy.venue.domain.view.Venue

internal class VenueInfoService(private val dbService: FirestoreDbService, private val authService: FirebaseAuthService) {

    fun venue(): Observable<Venue> {
        return authService.ifUserSignedInThenObservableFrom {
            dbService.venueInfo()
                .map { it.toVenue() }
        }
    }
}
