package com.untillDawn.Model.Enums;

import com.untillDawn.Model.App;

public enum AllTexts {
    signUp("sign up", "s'inscrire"),
    login("login", "se connecter"),
    guest("enter as guest", "entrer en tant qu'invité"),
    exit("exit", "quitter"),
    enterUsername("enter username...", "entrez le nom d'utilisateur..."),
    enterNewUsername("enter your new username...", "entrez votre nouveau nom d'utilisateur..."),
    enterPassword("enter password...", "entrez le mot de passe..."),
    enterNewPassword("enter your new password...", "entrez votre nouveau mot de passe..."),
    enterAnswer("enter your answer...", "entrez votre réponse..."),
    enterAllFieldsError("you must fill all the fields!", "vous devez remplir tous les champs !"),
    notAvailableUsernameError("this username is not available!", "ce nom d'utilisateur n'est pas disponible !"),
    passwordWeakError("password is weak!", "le mot de passe est faible !"),
    passwordTooShortError("password is too short!", "le mot de passe est trop court !"),
    noSuchUserError("no such user exists!", "aucun utilisateur trouvé !"),
    wrongPasswordError("wrong password!", "mot de passe incorrect !"),
    enterUsernameError("please enter your username!", "veuillez entrer votre nom d'utilisateur !"),
    enterAnswerError("please enter the correct answer!", "veuillez entrer la bonne réponse !"),
    yourPasswordIs("your password is ", "votre mot de passe est "),
    back("back", "retour"),
    play("play", "jouer"),
    submit("submit", "valider"),
    forgetPassword("forget password", "mot de passe oublié"),
    newGame("new game", "nouvelle partie"),
    loadGame("load game", "charger partie"),
    settings("settings", "paramètres"),
    profile("profile", "profil"),
    scoreboard("scoreboard", "classement"),
    hint("hint", "indice"),
    logout("logout", "se déconnecter"),
    volume("volume", "volume"),
    selectedMusicTrack("selected music track:", "piste musicale sélectionnée :"),
    selectedLanguage("selected Language:", "langue sélectionnée :"),
    SFX("SFX", "effets sonores"),
    autoReload("auto reload", "rechargement automatique"),
    grayScale("gray scale", "niveaux de gris"),
    keybindings("keybindings", "raccourcis clavier"),
    apply("apply", "appliquer"),
    changeAvatar("change avatar", "changer d'avatar"),
    deleteAccount("delete account", "supprimer le compte"),
    enterDifferentUsernameError("enter different username!", "entrez un nom d'utilisateur différent !"),
    enterDifferentPasswordError("enter different password!", "entrez un mot de passe différent !"),
    usernameChanged("username changed successfully!", "nom d'utilisateur changé avec succès !"),
    passwordChanged("password changed successfully!", "mot de passe changé avec succès !"),
    gameFinished("Game Finished", "Partie terminée"),
    username("Username:", "Nom d'utilisateur :"),
    timeSurvived("Time Survived:", "Temps de survie :"),
    kills("Kills:", "Éliminations :"),
    score("Score:", "Score :"),
    youWon("YOU WON!", "VOUS AVEZ GAGNÉ !"),
    youDied("YOU DIED", "VOUS ÊTES MORT"),
    youGaveUp("YOU GAVE UP", "VOUS AVEZ ABANDONNÉ"),
    continueText("Continue", "Continuer"),
    pauseTitle("Pause Menu", "Menu Pause"),
    resume("Resume", "Reprendre"),
    showCheatCodes("Show Cheat Codes", "Afficher les codes de triche"),
    showAbilities("Show Abilities", "Afficher les capacités"),
    toggleGrayScale("Toggle Grayscale", "Activer/désactiver les niveaux de gris"),
    saveAndExit("Save & Exit", "Sauvegarder et quitter"),
    giveUp("Give Up", "Abandonner"),
    cheatDialogText("U: fast forward 1min\nI: level up\nJ: fill hp\nK: trigger boss fight\nM: increase speed",
        "U : avancer de 1min\nI : monter de niveau\nJ : remplir la vie\nK : déclencher le boss\nM : augmenter la vitesse"),
    abilitiesDialogText("List of abilities player has obtained...", "Liste des capacités obtenues par le joueur..."),
    ok("OK", "OK"),
    HINTS_TITLE("Hero Hints", "Conseils des héros"),
    SHANA_HINT("The Flame Whisperer who dances with the fire of eternity.", "La murmureuse de flammes qui danse avec le feu de l'éternité."),
    DIAMOND_HINT("The Unbreakable Shield, whose resolve never cracks.", "Le bouclier incassable, dont la détermination ne faillit jamais."),
    SCARLETT_HINT("Swift as the crimson wind, the shadow of the battlefield.", "Rapide comme le vent cramoisi, l'ombre du champ de bataille."),
    LILITH_HINT("The Enchantress who bends fate with a single glance.", "L'enchanteresse qui plie le destin d'un seul regard."),
    DASHER_HINT("A streak of lightning, faster than the eye can see.", "Un éclair, plus rapide que l'œil ne peut voir."),
    ACTIVE_KEY_BINDINGS("Active Key Bindings", "Raccourcis Clavier Actifs"),
    CHEAT_CODES("Cheat Codes", "Codes de triche"),
    ABILITIES("Abilities", "Capacités"),
    CHEAT_U("~>U: - Fast forward 1min", "~>U : - Avance rapide de 1 min"),
    CHEAT_I("~>I: - Level up", "~>I : - Monter de niveau"),
    CHEAT_J("~>J: - Fill hp", "~>J : - Remplir la vie"),
    CHEAT_K("~>K: - trigger boss fight", "~>K : - Lancer un combat de boss"),
    CHEAT_M("~>M: - increase speed", "~>M : - Augmenter la vitesse"),
    ABILITY_VITALITY("~>VITALITY: - Add one hp to your maximum hp", "~>VITALITY : - Ajoute un point de vie au maximum"),
    ABILITY_DAMAGER("~>DAMAGER: - Increase your weapon's damage by 25% for 10 seconds", "~>DAMAGER : - Augmente les dégâts de l'arme de 25% pendant 10s"),
    ABILITY_PROCREASE("~>PROCREASE: - Add one projectile to you weapon", "~>PROCREASE : - Ajoute un projectile à votre arme"),
    ABILITY_AMMOCREASE("~>AMMOCREASE: - Increase your weapon's maximum ammo by 5", "~>AMMOCREASE : - Augmente le max de munitions de 5"),
    ABILITY_SPEEDY("~>SPEEDY: - Double your speed for 10 seconds", "~>SPEEDY : - Double votre vitesse pendant 10s");;

    private final String english;
    private final String french;

    AllTexts(String english, String french) {
        this.english = english;
        this.french = french;
    }

    public String getEnglish() {
        return english;
    }

    public String getFrench() {
        return french;
    }

    public String getVal(Language language) {
        if(language.equals(Language.french)) {
            return french;
        }
        return english;
    }

    public String getVal() {
        return getVal(App.getInstance().getLanguage());
    }
}
